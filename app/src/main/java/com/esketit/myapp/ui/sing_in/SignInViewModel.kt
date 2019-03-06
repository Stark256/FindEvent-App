package com.esketit.myapp.ui.sing_in

import android.arch.lifecycle.ViewModel
import com.esketit.myapp.managers.Injector
import com.esketit.myapp.models.firebase.FirebaseResponse

class SignInViewModel: ViewModel(){

    fun signInPressed(email: String, pass: String, response: (FirebaseResponse) -> Unit){
        Injector.emailAuth.signIn(email, pass, { firebaseResponse -> response(firebaseResponse) })
    }

}
