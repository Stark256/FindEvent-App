package com.esketit.myapp.services

import com.esketit.myapp.managers.Injector
import com.esketit.myapp.models.firebase.FirebaseResponse
import com.esketit.myapp.models.firebase.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class EmailAuthService{

    private val auth = FirebaseAuth.getInstance()

    val currentUser: FirebaseUser?
        get(){ return auth.currentUser }

    val isUserLogged: Boolean = (auth.currentUser != null)

    fun signUp(email: String, pass: String, name: String, response: (FirebaseResponse) -> Unit){
        auth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener { task: Task<AuthResult> ->
                if(task.isSuccessful){
                    currentUser?.let {
                        val user = User(email = email,
                            name = name,
                            id = it.uid)
                        Injector.services.userRepository.createUser(user, { firebaseResponse ->
                            response(FirebaseResponse(firebaseResponse.success, firebaseResponse.exception))
                        })
                    }
                }else{
                    response(FirebaseResponse(false, task.exception))
                }
            }
    }

    fun signOut(){ auth.signOut() }

}
