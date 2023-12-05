package com.example.flightapp.model

import java.io.Serializable

data class Flight(
    val id: Int,
    val flightNumber: String? = null,
    var departure: String? = null,
    var arrival: String? = null,
    var departureTime: String? = null,
    var arrivalTime: String? = null,
    var price: Double? = null,
    val company: String? = null,
    val transactions: List<Transaction>? = null
) : Serializable