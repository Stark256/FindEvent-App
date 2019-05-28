package com.esketit.myapp.ui.main.friends

import android.arch.lifecycle.ViewModel
import com.esketit.myapp.managers.Injector
import com.esketit.myapp.models.firebase.FirebaseResponse
import com.esketit.myapp.models.firebase.User
import com.esketit.myapp.models.local.friends_models.FriendsRequestsModel

class FriendsViewModel: ViewModel(){


    fun loadFriends(firebaseResponse: (FirebaseResponse, ArrayList<User>?) -> Unit) {
        Injector.services.userRepository.getFriends(Injector.userManager.activeUser!!.id, firebaseResponse)
    }

    fun loadRequests(firebaseResponse: (FirebaseResponse, ArrayList<FriendsRequestsModel>?) -> Unit) {
        Injector.userManager.activeUser?.id?.let {
            Injector.services.friendsRequestRepository.getRequests(it, firebaseResponse)
        }
    }

    fun loadSent(firebaseResponse: (FirebaseResponse, ArrayList<FriendsRequestsModel>?) -> Unit) {
        Injector.userManager.activeUser?.id?.let {
            Injector.services.friendsRequestRepository.getSent(it, firebaseResponse)
        }
    }
}
