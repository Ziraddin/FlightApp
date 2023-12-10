package com.example.flightapp.ui.adapters.recyclerview

data class Seats(
    val imgSrc : Int ,
    val seatNumber : String ,
    var isSelected: Boolean = false ,
    var notAvailable :Boolean = false
)
