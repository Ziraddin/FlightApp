package com.example.flightapp.model

import java.io.Serializable

data class Payment(
    var id: Int,
    var paymentType: String? = null,
    val users: List<User>? = null
) : Serializable