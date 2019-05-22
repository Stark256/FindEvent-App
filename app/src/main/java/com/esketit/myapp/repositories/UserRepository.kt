package com.esketit.myapp.repositories

import com.esketit.myapp.models.firebase.FirebaseResponse
import com.esketit.myapp.models.firebase.User
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.GeoPoint

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
        db.collection(COLLECTION_USER).document(userID).update(User.Key.cordinateKey.value, cordinate)
            .addOnCompleteListener {
                firebaseResponse(FirebaseResponse(it.isSuccessful, it.exception))
            }

    }

    fun getFriends(userID: String, firebaseResponse: (FirebaseResponse) -> Unit) {
        db.collection(COLLECTION_USER).document(userID).collection(COLLECTION_FRIENDS).get()
            .addOnSuccessListener {
                // TODO resolve conflicts and get friends
                // TODO research to get friends like User models or references
//                it.documents
//                (it.documents[0].data.get("user") as DocumentReference).get()
                firebaseResponse(FirebaseResponse(false, null))
            }.addOnFailureListener {
                firebaseResponse(FirebaseResponse(false, it))
            }

    }

}
