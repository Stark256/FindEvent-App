package com.esketit.myapp.ui.main

import android.arch.lifecycle.ViewModel
import com.esketit.myapp.managers.Injector
import java.util.*

class MainViewModel: ViewModel(){

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

}
