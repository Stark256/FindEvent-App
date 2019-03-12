package com.esketit.myapp.application

import android.app.Application
import android.content.ComponentCallbacks2
import com.esketit.myapp.managers.Injector


class App: Application() {//, ComponentCallbacks2

    companion object {
        @JvmStatic
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this


        BackgroundManager.getBackgroundManager(this).registerListener(BackgroundManagerListener())
//        registerComponentCallbacks(this)

        Injector.initData()
    }


    private class BackgroundManagerListener: BackgroundManager.Listener{
        override fun onBecameForeground() {
            // TODO TEST

        }

        override fun onBecameBackground() {
            // TODO TEST

        }
    }

}
