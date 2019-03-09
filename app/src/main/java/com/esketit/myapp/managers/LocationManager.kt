package com.esketit.myapp.managers

import android.app.Activity
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.location.LocationServices


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



}
