package com.esketit.myapp.managers

import com.esketit.myapp.services.EmailAuthService
import com.esketit.myapp.services.FirebaseServices
import com.google.firebase.auth.FirebaseAuth

object Injector{
    lateinit var emailAuth: EmailAuthService

    lateinit var themesManager: ThemesManager

    lateinit var services: FirebaseServices

    fun initData(){
        emailAuth = EmailAuthService()
        themesManager = ThemesManager()
        services = FirebaseServices()
    }
}
