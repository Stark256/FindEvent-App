package com.esketit.myapp.application

import android.app.Application
import android.content.ComponentCallbacks2
import com.esketit.myapp.managers.Injector


class App: Application() {

    companion object {
        @JvmStatic
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        Injector.initData()


        BackgroundManager.getBackgroundManager(this).registerListener(BackgroundManagerListener())
    }


    private class BackgroundManagerListener: BackgroundManager.Listener{
        override fun onBecameForeground() {
            Injector.userManager.setUserOnline {  }

        }

        override fun onBecameBackground() {
            Injector.userManager.setUserOffline {  }
        }
    }

}
