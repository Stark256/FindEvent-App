package com.esketit.myapp.managers

import android.content.Context
import android.support.v7.app.AppCompatActivity
import com.esketit.myapp.util.PermissionManager
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*

const val UPDATE_INTERVAL_IN_MILLISECONDS: Long = 10000
const val FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = UPDATE_INTERVAL_IN_MILLISECONDS / 2

class LocationHelper(var activity: AppCompatActivity?){
    private val lock = Any()
    private var apiClient: FusedLocationProviderClient? = null
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationSettingRequest: LocationSettingsRequest
    private var settingsClient: SettingsClient? = null

    fun init(context: Context){
        if(Injector.permissionManager.isPermissionLocationGranted(context)){
            connect()
        }
    }

    fun connect() {
        synchronized(lock) {
            if (apiClient == null) {
                initApiClient()
            }
        }
    }

    private fun initApiClient() {
        activity?.let {
            apiClient = LocationServices.getFusedLocationProviderClient(it)
            settingsClient = LocationServices.getSettingsClient(it)

            createLocationRequest()
            buildLocationSettingsRequest()

            startLocationUpdates(it)
        }

    }

    private fun createLocationRequest() {
        locationRequest = LocationRequest().apply {
            interval = UPDATE_INTERVAL_IN_MILLISECONDS
            fastestInterval = FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
    }

    private fun buildLocationSettingsRequest() {
        val builder = LocationSettingsRequest.Builder()
        builder.addLocationRequest(locationRequest)
        builder.setAlwaysShow(true)
        locationSettingRequest = builder.build()
        settingsClient?.checkLocationSettings(locationSettingRequest)

    }

    private fun startLocationUpdates(activity: AppCompatActivity){
        settingsClient?.checkLocationSettings(locationSettingRequest)
            ?.addOnFailureListener {exception ->
                if(exception is ResolvableApiException){
                    if(exception.statusCode == LocationSettingsStatusCodes.RESOLUTION_REQUIRED){
                        exception.startResolutionForResult(
                            activity,
                            PermissionManager.RESULT_LOCATION);
                    }
                }
            }
    }

}