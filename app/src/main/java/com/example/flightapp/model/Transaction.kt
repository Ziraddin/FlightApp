package com.example.flightapp.model

import java.util.Date

data class Transaction(
    val id: Int,
    var date: Date? = null,
    var flightNumber: String? = null,
    var baggage: String? = null,
    var seatNumber: String? = null,
    val user: User,
    val flight: Flight,
)
