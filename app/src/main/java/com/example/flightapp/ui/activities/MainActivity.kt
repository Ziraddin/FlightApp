package com.example.flightapp.ui.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.flightapp.R
import com.example.flightapp.databinding.ActivityMainBinding
import com.facebook.FacebookSdk
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mAuth: FirebaseAuth
    private lateinit var navController: NavController

    override fun onStart() {
        super.onStart()
        if (mAuth.currentUser != null) {
            navController.navigate(R.id.action_viewPagerFragment_to_homeFragment)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
        binding = ActivityMainBinding.inflate(layoutInflater)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        FacebookSdk.setClientToken("b70c49a212f1a6eeb5372eb96dcde0e1")
        FacebookSdk.sdkInitialize(applicationContext)
        setContentView(binding.root)

        val bottomNav = binding.bottomNav
        bottomNav.itemIconTintList = ContextCompat.getColorStateList(this, R.color.bottomnav)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomNav, navController)
    }

    fun setBottomNavigation(visibility: Boolean) {
        if (visibility) {
            binding.bottomNav.visibility = View.VISIBLE
            binding.bottomNav.itemIconTintList = null;

        } else {
            binding.bottomNav.visibility = View.GONE
        }
    }

}