package com.esketit.myapp.services

import com.esketit.myapp.repositories.FriendsRequestRepository
import com.esketit.myapp.repositories.UserRepository

class FirebaseServices {

    val storageService = FirebaseStorageService()
    val userRepository = UserRepository()
    val friendsRequestRepository = FriendsRequestRepository()

}
