package com.esketit.myapp.repositories

import com.google.firebase.firestore.FirebaseFirestore

class UserRepository{

    val COLLECTION_USER = "users"


    val db = FirebaseFirestore.getInstance()

    fun addUser(userID: String){
        db.collection(COLLECTION_USER).document(userID).set("")
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

    /*Map<String, Object> user = new HashMap<>();
user.put("first", "Alan");
user.put("middle", "Mathison");
user.put("last", "Turing");
user.put("born", 1912);*/

}
