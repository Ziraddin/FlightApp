package com.example.flightapp.model

data class Payment(
    val id: Int,
    var paymentType: String? = null,
    val users: List<User>? = null
)