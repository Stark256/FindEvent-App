package com.esketit.myapp.managers

import com.esketit.myapp.application.App
import com.esketit.myapp.services.EmailAuthService
import com.esketit.myapp.services.FirebaseServices
import com.esketit.myapp.util.PermissionManager

object Injector{

    var application: App? = null

    lateinit var auth: EmailAuthService

    lateinit var services: FirebaseServices

    lateinit var locationManager: LocationManager

    lateinit var permissionManager: PermissionManager

    lateinit var userManager: UserManager

    lateinit var storageManager: StorageManager

    fun initData(){
        auth = EmailAuthService()

        services = FirebaseServices()

        permissionManager = PermissionManager()

        locationManager = LocationManager()

        userManager = UserManager()

        storageManager = StorageManager()

    }
}
