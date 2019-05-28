package com.esketit.myapp.repositories

import com.esketit.myapp.managers.Injector
import com.esketit.myapp.models.firebase.FirebaseResponse
import com.esketit.myapp.models.firebase.FriendRequest
import com.esketit.myapp.models.firebase.FriendRequestStatus
import com.esketit.myapp.models.firebase.User
import com.esketit.myapp.models.local.friends_models.FriendsRequestsModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.reflect.TypeToken
import io.reactivex.Observable
import kotlin.collections.ArrayList

class FriendsRequestRepository {

    val COLLECTION_FRIEND_REQUESTS = "friend_requests"

    val db = FirebaseFirestore.getInstance()

    fun createRequest(request: FriendRequest, firebaseResponse: (FirebaseResponse) -> Unit) {
        db.collection(COLLECTION_FRIEND_REQUESTS).document(request.id).set(request.data())
            .addOnSuccessListener {
                firebaseResponse(FirebaseResponse(true, null))
            }.addOnFailureListener {
                firebaseResponse(FirebaseResponse(false, it))
            }
    }

    fun updateRequestStatus(request: FriendRequest, firebaseResponse: (FirebaseResponse) -> Unit) {
        db.collection(COLLECTION_FRIEND_REQUESTS).document(request.id)
            .update(FriendRequest.Key.statusKey.value, request.status)
            .addOnSuccessListener {
                firebaseResponse(FirebaseResponse(true, null))
            }.addOnFailureListener {
                firebaseResponse(FirebaseResponse(false, it))
            }
    }

    fun requestStatusAccepted(requestID: String, firebaseResponse: (FirebaseResponse) -> Unit) {
        db.collection(COLLECTION_FRIEND_REQUESTS).document(requestID)
            .update(FriendRequest.Key.statusKey.value, FriendRequestStatus.ACCEPTED.value)
            .addOnSuccessListener {
                firebaseResponse(FirebaseResponse(true, null))
            }.addOnFailureListener {
                firebaseResponse(FirebaseResponse(false, it))
            }
    }

    fun requestStatusDeclined(requestID: String, firebaseResponse: (FirebaseResponse) -> Unit) {
        db.collection(COLLECTION_FRIEND_REQUESTS).document(requestID)
            .update(FriendRequest.Key.statusKey.value, FriendRequestStatus.DECLINED.value)
            .addOnSuccessListener {
                firebaseResponse(FirebaseResponse(true, null))
            }.addOnFailureListener {
                firebaseResponse(FirebaseResponse(false, it))
            }
    }

    fun getRequests(currentUserID: String, firebaseResponse: (FirebaseResponse, ArrayList<FriendsRequestsModel>?) -> Unit) {
        db.collection(COLLECTION_FRIEND_REQUESTS)
            .whereEqualTo(FriendRequest.Key.receiverKey.value, currentUserID)
            .whereEqualTo(FriendRequest.Key.statusKey.value, FriendRequestStatus.PENDING.value)
            .get().addOnSuccessListener {

                val arr = ArrayList<FriendRequest>()

                it.documents.forEach {
                    it.toObject(FriendRequest::class.java)?.let {
                        arr.add(it)
                    }
                }

                Observable.fromIterable(arr)
                    .flatMap { Injector.services.userRepository.getUserObservable(it, false) }
                    .toList().toObservable()
                    .map { result -> result }
                    .subscribe({t: MutableList<FriendsRequestsModel> ->
                        val result = ArrayList<FriendsRequestsModel>()
                        t.forEach { result.add(it) }
                        firebaseResponse(FirebaseResponse(true, null), result)
                    }, {
                        firebaseResponse(FirebaseResponse(false, it), null)
                    })

            }.addOnFailureListener {
                firebaseResponse(FirebaseResponse(false, it), null)
            }
    }

    fun getSent(currentUserID: String, firebaseResponse: (FirebaseResponse, ArrayList<FriendsRequestsModel>?) -> Unit) {
        db.collection(COLLECTION_FRIEND_REQUESTS)
            .whereEqualTo(FriendRequest.Key.senderKey.value, currentUserID)
            .whereEqualTo(FriendRequest.Key.statusKey.value, FriendRequestStatus.PENDING.value)
            .get().addOnSuccessListener {
               val arr = ArrayList<FriendRequest>()

               it.documents.forEach {
                   it.toObject(FriendRequest::class.java)?.let {
                       arr.add(it)
                   }
               }

                Observable.fromIterable(arr)
                    .flatMap { Injector.services.userRepository.getUserObservable(it, true) }
                    .toList().toObservable()
                    .map { result -> result }
                    .subscribe({t: MutableList<FriendsRequestsModel> ->
                        val result = ArrayList<FriendsRequestsModel>()
                        t.forEach { result.add(it) }
                        firebaseResponse(FirebaseResponse(true, null), result)
                    }, {
                        firebaseResponse(FirebaseResponse(false, it), null)
                    })

            }.addOnFailureListener {
                firebaseResponse(FirebaseResponse(false, it), null)
            }
    }


}
