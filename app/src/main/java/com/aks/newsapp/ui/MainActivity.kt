package com.aks.newsapp.ui

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aks.newsapp.adapter.NewsAdapter
import com.aks.newsapp.databinding.ActivityMainBinding
import com.aks.newsapp.model.ArticlesItem
import com.aks.newsapp.viewmodel.NewsViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.util.*

private const val PERMISSION_REQUEST_ACCESS_CODE = 100

class MainActivity : AppCompatActivity(), NewsAdapter.NewsArticleClicked {
    private lateinit var binding : ActivityMainBinding
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var newsViewModel: NewsViewModel
    var data = MutableLiveData<List<ArticlesItem>>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //create an instance of fused location provider client
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        getCurrentLocation()

        binding.newsList.layoutManager = LinearLayoutManager(this)


        newsViewModel = ViewModelProvider(this)[NewsViewModel::class.java]

        newsViewModel.article.observe(this) {
            data.value = it.articles
            binding.newsList.adapter = NewsAdapter(data, this)
        }

    }

    //function to get the current location of the user
    private fun getCurrentLocation() {
        //check if user gave the permission to access the location
        if(checkPermission()){
            //check if user has disabled the location
            if(isLocationEnabled()){
                //get the location
                fusedLocationProviderClient.lastLocation.addOnCompleteListener(this) { task ->
                    val location : Location? = task.result
                    if(location==null) Toast.makeText(
                        this,
                        "Unable to fetch location",
                        Toast.LENGTH_SHORT
                    ).show()

                    else {
                        Toast.makeText(this,
                            "Location fetched successfully",
                            Toast.LENGTH_SHORT)
                            .show()

                        val city = getCity(location.latitude, location.longitude)
                        val country = getCountry(location.latitude, location.longitude)

                        Log.d("location", "Latitude : ${location.latitude}")
                        Log.d("location", "Longitude : ${location.longitude}")
                        Log.d("location", "City : $city")
                        Log.d("location", "Country : $country")
                    }
                }
            }
            else{
                //go to settings to enable the location
                Toast.makeText(this, "Turn of the location", Toast.LENGTH_SHORT).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        }
        else{
            //request the permission
            requestPermission()
        }
    }

    //function to get the city name
    private fun getCity(lat : Double, long : Double) : String {
        val geocoder = Geocoder(this, Locale.getDefault())
        val address = geocoder.getFromLocation(lat, long, 1)
        return address[0].locality
    }

    //function to get the country name
    private fun getCountry(lat : Double, long : Double) : String {
        val geocoder = Geocoder(this, Locale.getDefault())
        val address = geocoder.getFromLocation(lat, long, 1)
        return address[0].countryName
    }

    //function to request permission
    private fun requestPermission() {
        ActivityCompat.requestPermissions(this,
            arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION,
                    android.Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSION_REQUEST_ACCESS_CODE)
    }


    //function to check if the permission is granted or not
    private fun checkPermission() : Boolean {
        if(ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(this,
            android.Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED) return true

        return false
    }

    //there are two ways to get the location
    //one is using the gps and other is to use the network
    //if any one of them is enabled, return true
    private fun isLocationEnabled() : Boolean {
        val locationManager : LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    //what to do if user gives permission
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode== PERMISSION_REQUEST_ACCESS_CODE){
            if(grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(applicationContext, "Permission Granted!!", Toast.LENGTH_SHORT).show()
                getCurrentLocation()
            }
            else{
                Toast.makeText(applicationContext, "Permission Denied!!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onArticleClicked(item: ArticlesItem) {
        Toast.makeText(this, item.headline, Toast.LENGTH_SHORT).show()

    }
}