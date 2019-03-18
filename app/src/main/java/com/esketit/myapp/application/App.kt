package com.esketit.myapp.application

import android.app.Application
import com.esketit.myapp.managers.Injector


class App: Application() {

    companion object {
        @JvmStatic
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        Injector.application = this
        Injector.initData()
    }
}
