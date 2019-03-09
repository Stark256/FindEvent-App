package com.esketit.myapp.managers

import com.esketit.myapp.services.EmailAuthService
import com.esketit.myapp.services.FirebaseServices
import com.esketit.myapp.util.PermissionManager

object Injector{
    lateinit var auth: EmailAuthService

    //lateinit var themesManager: ThemesManager

    lateinit var services: FirebaseServices
    lateinit var locationManager: LocationManager

    lateinit var permissionManager: PermissionManager

    fun initData(){
        auth = EmailAuthService()
       // themesManager = ThemesManager()
        services = FirebaseServices()
        permissionManager = PermissionManager()
        locationManager = LocationManager()
    }
}
