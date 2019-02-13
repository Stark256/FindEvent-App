package com.esketit.myapp.repositories

import com.esketit.myapp.models.firebase.User
import com.google.firebase.firestore.FirebaseFirestore

class UserRepository{

    val COLLECTION_USER = "users"


    val db = FirebaseFirestore.getInstance()

    fun createUser(user: User){
        db.collection(COLLECTION_USER).document(user.id).set(user.data())
            .addOnSuccessListener {  }
            .addOnFailureListener { exception -> }
    }

    fun getUser(userID: String){
        db.collection(COLLECTION_USER).document(userID).get()
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    task.result?.data
                }else{
                    task.exception
                }
            }
    }

}
