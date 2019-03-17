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
    }
}
