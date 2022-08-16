package com.aks.newsapp.utils

import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

object PermissionUtils {

    //function to request permission
    fun requestPermission(activity : AppCompatActivity, requestAccessCode : Int) {
        ActivityCompat.requestPermissions(activity,
            arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION),
            requestAccessCode
        )
    }

    //function to check if the permission is granted or not
    fun checkPermission(context : Context) : Boolean {
        if(ActivityCompat.checkSelfPermission(context,
                android.Manifest.permission.ACCESS_COARSE_LOCATION)== PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(context,
                android.Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED) return true

        return false
    }

    //there are two ways to get the location
    //one is using the gps and other is to use the network
    //if any one of them is enabled, return true
    fun isLocationEnabled(context : Context) : Boolean {
        val locationManager : LocationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

}