package com.esketit.myapp.services

import android.net.Uri
import com.esketit.myapp.managers.Injector
import com.esketit.myapp.models.firebase.FirebaseResponse
import com.esketit.myapp.models.firebase.User
import com.esketit.myapp.models.safeLet
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class EmailAuthService{

    private val auth = FirebaseAuth.getInstance()

    val currentUser: FirebaseUser?
        get(){ return auth.currentUser }

    val isUserLogged: Boolean
        get() { return (currentUser != null)}


    fun signUp(email: String, pass: String, name: String, avatarImgURL: Uri?, latitude: String, longitude: String, response: (FirebaseResponse) -> Unit){
        auth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener { task: Task<AuthResult> ->
                if(task.isSuccessful){
                    val currentUser = currentUser

                    if(avatarImgURL != null) {
                        currentUser?.let {
                            Injector.services.storageService.uploadAvatarImage(avatarImgURL,
                                currentUser.uid) { firebaseResponse, url ->
                                if (firebaseResponse.success) {
                                    safeLet(currentUser, url) { firebaseUser, avatarUrl ->
                                        val user = User(
                                            email = email,
                                            name = name,
                                            id = firebaseUser.uid,
                                            avatarImgURL = avatarUrl,
                                            latitude = latitude,
                                            longitude = longitude
                                        )
                                        Injector.services.userRepository.createUser(user) { firebaseResponse ->
                                            response(
                                                FirebaseResponse(
                                                    firebaseResponse.success,
                                                    firebaseResponse.exception
                                                )
                                            )
                                        }
                                    }
                                } else {
                                    response(firebaseResponse)
                                }
                            }
                        }
                    }else{
                        currentUser?.let {
                            val user = User(
                                email = email,
                                name = name,
                                id = it.uid,
                                latitude = latitude,
                                longitude = longitude)
                            Injector.services.userRepository.createUser(user) { firebaseResponse ->
                                response(FirebaseResponse(firebaseResponse.success, firebaseResponse.exception))
                            }
                        }
                    }
                }else{ response(FirebaseResponse(false, task.exception)) }
            }
    }


    fun signIn(email: String, pass: String, response: (FirebaseResponse) -> Unit){
        auth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener {
                response(FirebaseResponse(it.isSuccessful, it.exception))
            }
    }

    fun resetPassword(email: String, response: (FirebaseResponse) -> Unit){
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { response(FirebaseResponse(it.isSuccessful, it.exception)) }
    }

    fun signOut(){ auth.signOut() }

}
