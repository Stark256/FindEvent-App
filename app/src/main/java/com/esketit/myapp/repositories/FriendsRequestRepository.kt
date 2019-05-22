package com.esketit.myapp.repositories

import com.esketit.myapp.models.firebase.FirebaseResponse
import com.esketit.myapp.models.firebase.FriendRequest
import com.esketit.myapp.models.firebase.FriendRequestStatus
import com.google.firebase.firestore.FirebaseFirestore

class FriendsRequestRepository {

    val COLLECTION_FRIEND_REQUESTS = "friend_requests"

    val db = FirebaseFirestore.getInstance()

    fun createRequest(request: FriendRequest, firebaseResponse: (FirebaseResponse) -> Unit) {
        db.collection(COLLECTION_FRIEND_REQUESTS).document().set(request.data())
            .addOnSuccessListener {
                firebaseResponse(FirebaseResponse(true, null))
            }.addOnFailureListener {
                firebaseResponse(FirebaseResponse(false, it))
            }
    }

    fun updateRequestStatus(request: FriendRequest, firebaseResponse: (FirebaseResponse) -> Unit) {
        db.collection(COLLECTION_FRIEND_REQUESTS).document(request.id)
            .update(FriendRequest.Key.statusKey.value, request.status.value)
            .addOnSuccessListener {
                firebaseResponse(FirebaseResponse(true, null))
            }.addOnFailureListener {
                firebaseResponse(FirebaseResponse(false, it))
            }
    }



}
