package com.esketit.myapp.repositories

import com.esketit.myapp.models.firebase.FirebaseResponse
import com.esketit.myapp.models.firebase.User
import com.google.firebase.firestore.FirebaseFirestore

class UserRepository{

    val COLLECTION_USER = "users"


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

    fun getUser(userID: String, firebaseResponse: (FirebaseResponse, User?) -> Unit){
        db.collection(COLLECTION_USER).document(userID).get()
            .addOnSuccessListener { documentSnapshot ->
                firebaseResponse(FirebaseResponse(true, null), documentSnapshot.toObject(User::class.java))
            }.addOnFailureListener {
                firebaseResponse(FirebaseResponse(false, it), null)
            }
    }

    fun setIsOnline(isOnline: Boolean, userID: String, firebaseResponse: (FirebaseResponse) -> Unit){
        db.collection(COLLECTION_USER).document(userID).update(User.Key.isOnlineKey.value, isOnline).addOnCompleteListener {
            firebaseResponse(FirebaseResponse(it.isSuccessful, it.exception))
        }
    }

}
