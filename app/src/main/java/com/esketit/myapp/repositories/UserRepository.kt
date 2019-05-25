package com.esketit.myapp.repositories

import com.esketit.myapp.models.firebase.FirebaseResponse
import com.esketit.myapp.models.firebase.User
import com.google.firebase.firestore.*
import io.reactivex.Observable
import java.lang.NullPointerException

class UserRepository{

    val COLLECTION_USER = "users"
    val COLLECTION_FRIENDS = "friends"

    val db = FirebaseFirestore.getInstance()

    fun createUser(user: User, firebaseResponse: (FirebaseResponse) -> Unit){
        db.collection(COLLECTION_USER).document(user.id).set(user.data())
            .addOnSuccessListener {
                firebaseResponse(FirebaseResponse(true, null))
            }
            .addOnFailureListener { exception ->
                firebaseResponse(FirebaseResponse(false, exception))
            }
    }

    fun updateUser(user: User, firebaseResponse: (FirebaseResponse) -> Unit) {
        db.collection(COLLECTION_USER).document(user.id).update(user.data())
            .addOnSuccessListener {
                firebaseResponse(FirebaseResponse(true, null))
            }.addOnFailureListener {
                firebaseResponse(FirebaseResponse(false, it))
            }
    }

    fun getUser(userID: String, firebaseResponse: (FirebaseResponse, User?) -> Unit){
        db.collection(COLLECTION_USER).document(userID).get()
            .addOnSuccessListener { documentSnapshot ->
                firebaseResponse(FirebaseResponse(true, null), documentSnapshot.toObject(User::class.java))
            }.addOnFailureListener {
                firebaseResponse(FirebaseResponse(false, it), null)
            }
    }

    fun updateActiveTime(userID: String, firebaseResponse: (FirebaseResponse) -> Unit){
        db.collection(COLLECTION_USER).document(userID).update(User.Key.activeTimeKey.value, FieldValue.serverTimestamp())
            .addOnCompleteListener {
            firebaseResponse(FirebaseResponse(it.isSuccessful, it.exception))
        }
    }

    fun updateCordinate(userID: String, cordinate: GeoPoint, firebaseResponse: (FirebaseResponse) -> Unit) {
        db.collection(COLLECTION_USER).document(userID).update(User.Key.coordinateKey.value, cordinate)
            .addOnCompleteListener {
                firebaseResponse(FirebaseResponse(it.isSuccessful, it.exception))
            }

    }



    fun getFriends(userID: String, firebaseResponse: (FirebaseResponse, ArrayList<User>?) -> Unit) {
        db.collection(COLLECTION_USER).document(userID).collection(COLLECTION_FRIENDS).get()
            .addOnSuccessListener {

                Observable.fromIterable(it.documents).flatMap { getUserByReference(it) }
                    .toList().toObservable()
                    .map { result -> result }
                    .subscribe({ t: MutableList<User> ->
                        val friends = ArrayList<User>()
                        t.forEach { friends.add(it) }

                        firebaseResponse(FirebaseResponse(true, null), friends)
                    },{
                        firebaseResponse(FirebaseResponse(false, it), null)
                    })


            }.addOnFailureListener {
                firebaseResponse(FirebaseResponse(false, it), null)
            }

    }


    private fun getUserByReference(documentSnapshot: DocumentSnapshot) : Observable<User> {
        return Observable.create { emitter ->
            (documentSnapshot.get("user") as DocumentReference).get()
                .addOnSuccessListener { snapshot ->

                    val user = snapshot.toObject(User::class.java)

                    if (user != null ) {
                        emitter.onNext(user)
                        emitter.onComplete()
                    } else {
                        emitter.onError(NullPointerException())
                    }

            }.addOnFailureListener {
                emitter.onError(it)
            }
        }
    }

}
