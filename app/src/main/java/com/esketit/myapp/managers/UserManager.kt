package com.esketit.myapp.managers

import com.esketit.myapp.models.firebase.FirebaseResponse
import com.esketit.myapp.models.firebase.User

class UserManager{

    var activeUser: User? = null

    fun updateActiveUser(firebaseResponse: (FirebaseResponse) -> Unit){
        if(Injector.auth.isUserLogged){
            Injector.auth.currentUser?.let {
                Injector.services.userRepository.getUser(it.uid){ response, user ->
                    if(response.success){
                        this.activeUser = user
                        setUserOnline {}
                        firebaseResponse(response)
                    }
                    else{ firebaseResponse(response) }
                }
            }
        } else { firebaseResponse(FirebaseResponse(false, null)) }
    }

    fun setUserOnline(firebaseResponse: (FirebaseResponse) -> Unit){
        activeUser?.let {
            Injector.services.userRepository.setIsOnline(true, it.id){response ->  firebaseResponse(response) }
        }
    }

    fun setUserOffline(firebaseResponse: (FirebaseResponse) -> Unit){
        activeUser?.let {
                Injector.services.userRepository.setIsOnline(false, it.id){response ->  firebaseResponse(response) }
        }
    }

}
