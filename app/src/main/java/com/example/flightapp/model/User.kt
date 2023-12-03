package com.example.flightapp.model

import java.io.Serializable

data class User(
    var id: Int? = null,
    var firstname: String? = null,
    var lastname: String? = null,
    var email: String? = null,
    var password: String? = null,
    val transactions: List<Transaction>? = null,
    var payment: Payment? = null
) : Serializable
