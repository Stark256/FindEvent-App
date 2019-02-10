package com.esketit.myapp

import android.app.Application
import android.content.Context
import com.esketit.myapp.managers.Injector
import com.google.firebase.auth.FirebaseAuth

class App: Application(){

    companion object {
        @JvmStatic
        lateinit var instance: App
    }

    fun getContext(): Context {
        return this
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initInjector()
    }

    private fun initInjector(){
        Injector.auth = FirebaseAuth.getInstance()
    }
}
