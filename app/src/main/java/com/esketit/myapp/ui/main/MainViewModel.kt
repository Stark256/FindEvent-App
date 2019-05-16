package com.esketit.myapp.ui.main

import android.arch.lifecycle.ViewModel
import android.location.Location
import com.esketit.myapp.managers.Injector
import com.esketit.myapp.models.safeLet
import com.google.firebase.firestore.GeoPoint
import java.util.*

class MainViewModel: ViewModel(){

    val currentLocationEnabled: Boolean
        get() { return Injector.storageManager.getLocationEnabled() }

    fun setTimer(){
        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                Injector.userManager.updateActiveTime {
                        firebaseResponse ->
                }
            }
        }, 10000, 60000)
    }

    fun updateLocation(location: Location?) {
        // Todo update current location if user has enebled property in cache and permission
        safeLet(Injector.userManager.activeUser, location){ user, location ->
            Injector.services.userRepository.updateCordinate(user.id, GeoPoint(location.latitude, location.longitude)) {
                Injector.userManager.updateActiveUser {}
            }

        }
    }

}
