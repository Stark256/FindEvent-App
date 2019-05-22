package com.esketit.myapp.managers

import android.app.Activity
import android.content.Context
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.location.LocationServices
import java.lang.Exception
import java.util.*


class LocationManager{

    fun getLastLocation(context: AppCompatActivity): Location?{
        val man = (context.getSystemService(Context.LOCATION_SERVICE) as LocationManager)

        var loc: Location? = null

        for(item in man.getProviders(true)){

            if(loc == null){
                loc = man.getLastKnownLocation(item)
            }
        }
        return loc

    }


    fun getAddress(context: Context, lat: Double, lon: Double): String? {
        // Lviv, Ivana Poliuia Streen, 15

        try {
            val geocoder = Geocoder(context, Locale.getDefault())
            val addresses = geocoder.getFromLocation(lat, lon, 1)
            val lastAddress = addresses.last()

            val address1 = lastAddress.thoroughfare
            val address2 = lastAddress.featureName
            val city = lastAddress.locality

            return "${city}, ${address1}, ${address2}"
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

}
