package com.esketit.myapp.services

import android.net.Uri
import com.esketit.myapp.models.firebase.FirebaseResponse
import com.google.firebase.storage.FirebaseStorage
import java.io.File
import java.util.*

class FirebaseStorageService{

    private val storage = FirebaseStorage.getInstance().reference

    private val REF_AVATAR_IMAGES = "avatarImages/"

    private val randomFileName: String
        get(){ return UUID.randomUUID().toString() }

    fun uploadAvatarImage(uri: Uri, userID: String, firebaseResponse: (FirebaseResponse, String?) -> Unit){
        storage.child("${REF_AVATAR_IMAGES}${userID}")
            .putFile(uri).addOnSuccessListener {
                it.storage.downloadUrl.addOnCompleteListener {
                    if(it.isSuccessful){ firebaseResponse(FirebaseResponse(true, null), it.result.toString()) }
                    else{ firebaseResponse(FirebaseResponse(false, it.exception), null) }
                }
            }.addOnFailureListener { exception -> firebaseResponse(FirebaseResponse(false, exception), null) }
    }

    fun downloadAvatarImage(){
//        val file = File.createTempFile(REF_AVATAR_IMAGES, "jpg")
//        storage.getFile()
    }

}
