package com.example.flightapp.api.constants

import com.example.flightapp.model.User
import com.google.firebase.auth.FirebaseAuth

object Constants {
    private val mAuth = FirebaseAuth.getInstance()
    private val currentUser = mAuth.currentUser!!
    val cUser = User(
        firstname = currentUser.displayName!!.split(" ")[0],
        lastname = currentUser.displayName!!.split(" ")[1],
        email = currentUser.email.toString()
    )
    const val url = "https://spring-flight-app-project.onrender.com/"
}