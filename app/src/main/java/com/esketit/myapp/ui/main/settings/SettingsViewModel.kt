package com.esketit.myapp.ui.main.settings

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.esketit.myapp.managers.Injector
import com.esketit.myapp.models.firebase.User

class SettingsViewModel: ViewModel(){

    var user = MutableLiveData<User>()

    fun updateApterItems(){
        user.value = Injector.userManager.activeUser
    }


}
