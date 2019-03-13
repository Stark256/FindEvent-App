package com.esketit.myapp.ui.main.settings

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.esketit.myapp.managers.Injector
import com.esketit.myapp.models.firebase.FirebaseResponse
import com.esketit.myapp.models.firebase.User
import com.esketit.myapp.models.local.settings_models.SettingsBaseItem
import com.esketit.myapp.models.local.settings_models.SettingsEmptyItem
import com.esketit.myapp.models.local.settings_models.SettingsProfileItem

class SettingsViewModel: ViewModel(){

    val user = MutableLiveData<User>()
    var settingsItems = MutableLiveData<ArrayList<SettingsBaseItem>>()
    var exception = MutableLiveData<FirebaseResponse>()

    fun loadCurrentUser(){
        Injector.auth.currentUser?.let {
            Injector.services.userRepository.getUser(it.uid){firebaseResponse, user ->
                if(firebaseResponse.success){ generateItems(user) }
                else{ exception.value = firebaseResponse }
            }
        }
    }


    private fun generateItems(user: User?){
        val arr = ArrayList<SettingsBaseItem>()

        user?.let { arr.add(SettingsProfileItem(user)) }
        arr.add(SettingsEmptyItem())
        user?.let { arr.add(SettingsProfileItem(user)) }

        settingsItems.value = arr
    }


}
