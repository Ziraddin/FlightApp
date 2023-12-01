package com.example.flightapp.model

data class User(
    val id: Int,
    var firstname: String? = null,
    var lastname: String? = null,
    var email: String? = null,
    var password: String? = null,
    val transactions: List<Transaction>? = null,
    val payment: Payment? = null
)
