package com.esketit.myapp

import android.app.Application
import android.content.Context
import android.content.Intent
import com.esketit.myapp.managers.Injector
import com.esketit.myapp.managers.ThemesManager
import com.esketit.myapp.services.EmailAuthService
import com.esketit.myapp.ui.welcome.WelcomeActivity
import com.google.firebase.auth.FirebaseAuth

class App: Application(){

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
