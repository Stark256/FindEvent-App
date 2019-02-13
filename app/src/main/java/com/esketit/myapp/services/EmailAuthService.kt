package com.esketit.myapp.services

import android.net.Uri
import android.util.Log
import com.esketit.myapp.managers.Injector
import com.esketit.myapp.models.firebase.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.android.synthetic.main.activity_sign_up.*

class EmailAuthService{

    private val auth = FirebaseAuth.getInstance()

    val currentUser: FirebaseUser?
        get(){ return auth.currentUser }

    val isUserLogged: Boolean = (auth.currentUser != null)


    fun update(email: String, name: String){
        currentUser?.let {
            val up = UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                //.setPhotoUri(Uri.parse("https://example.com/jane-q-user/profile.jpg"))
                .build()

            it.updateProfile(up)
                .addOnCompleteListener {
                        task ->

                    val user = User(email = email,
                        name = name,
                        id = it.uid)


                    if(task.isSuccessful){
                        currentUser?.let {
                            Injector.services.userRepository.createUser(user)
                        }
                    }else{

                    }
                }
        }
    }



    fun signUp(email: String, pass: String, name: String){
        auth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener { task: Task<AuthResult> ->
                if(task.isSuccessful){
                    update(email, name)

                }else{

                }
            }
            .addOnFailureListener {
                exception ->

            }
    }

    fun signOut(){ auth.signOut() }

}
