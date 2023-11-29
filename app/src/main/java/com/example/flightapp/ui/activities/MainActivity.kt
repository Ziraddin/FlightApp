package com.example.flightapp.ui.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.flightapp.R
import com.example.flightapp.databinding.ActivityMainBinding
import com.facebook.FacebookSdk

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        FacebookSdk.setClientToken("b70c49a212f1a6eeb5372eb96dcde0e1")
        FacebookSdk.sdkInitialize(applicationContext)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        NavigationUI.setupWithNavController(binding.bottomNav,navHostFragment.navController)
    }

   fun setBottomNavigation(visibility:Boolean){
       if(visibility){
           binding.bottomNav.visibility = View.VISIBLE
           binding.bottomNav.itemIconTintList = null;

       }else{
           binding.bottomNav.visibility = View.GONE
       }
   }

}