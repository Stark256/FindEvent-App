package com.esketit.myapp.managers

import com.esketit.myapp.models.firebase.FirebaseResponse
import com.esketit.myapp.models.firebase.User
import java.util.*

class UserManager{

    var activeUser: User? = null

    fun updateActiveUser(firebaseResponse: (FirebaseResponse) -> Unit){
        if(Injector.auth.isUserLogged){
            Injector.auth.currentUser?.let {
                Injector.services.userRepository.getUser(it.uid){ response, user ->
                    if(response.success){
                        this.activeUser = user
                        updateActiveTime{}
                        firebaseResponse(response)
                    }
                    else{ firebaseResponse(response) }
                }
            }
        } else { firebaseResponse(FirebaseResponse(false, null)) }
    }

    fun updateActiveTime(firebaseResponse: (FirebaseResponse) -> Unit){
        activeUser?.let {
            Injector.services.userRepository.updateActiveTime(it.id) { response ->  firebaseResponse(response) }
        }

    }

}
