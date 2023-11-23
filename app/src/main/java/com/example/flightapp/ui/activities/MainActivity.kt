package com.example.flightapp.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
    }
}