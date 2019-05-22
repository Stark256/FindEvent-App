package com.esketit.myapp.repositories

import com.esketit.myapp.models.firebase.FirebaseResponse
import com.esketit.myapp.models.firebase.User
import com.google.firebase.firestore.DocumentReference
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

    fun getFriends(userID: String, firebaseResponse: (FirebaseResponse, ArrayList<User>?) -> Unit) {
        db.collection(COLLECTION_USER).document(userID).collection(COLLECTION_FRIENDS).get()
            .addOnSuccessListener {
                // TODO refactor the code below

                val friends = ArrayList<User>()
                val documentsSize = it.documents.size
                for (item in it.documents) {

                    (item.data?.get("user") as DocumentReference).get()
                        .addOnCompleteListener { documentSnapshot ->

                        if (documentSnapshot.isSuccessful) {
                            documentSnapshot.result?.toObject(User::class.java)?.let {
                                friends.add(it)
                                if (documentsSize == friends.size) {
                                    firebaseResponse(FirebaseResponse(true, null), friends)
                                }
                            }
                        }

                    }
                }


            }.addOnFailureListener {
                firebaseResponse(FirebaseResponse(false, it), null)
            }

    }

}
