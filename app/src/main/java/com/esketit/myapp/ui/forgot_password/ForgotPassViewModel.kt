package com.esketit.myapp.ui.forgot_password

import android.arch.lifecycle.ViewModel
import com.esketit.myapp.managers.Injector
import com.esketit.myapp.models.firebase.FirebaseResponse

class ForgotPassViewModel: ViewModel(){

    fun resetPassword(email: String, response: (FirebaseResponse) -> Unit){
        Injector.auth.resetPassword(email, { firebaseResponse -> response(firebaseResponse) })
    }

}
