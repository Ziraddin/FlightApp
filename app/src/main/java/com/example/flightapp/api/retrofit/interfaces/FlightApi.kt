package com.example.flightapp.api.retrofit.interfaces

import com.example.flightapp.api.constants.Constants.flights
import com.example.flightapp.api.constants.Constants.transactions
import com.example.flightapp.model.Flight
import com.example.flightapp.model.Transaction
import com.example.flightapp.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface FlightApi {
    @GET("${flights}/getFlightsByArrivalDepartureAndDepartureTime/")
    fun getFlightsByArrivalDepartureAndDepartureTime(
        @Query("arrival") arrival: String,
        @Query("departure") departure: String,
        @Query("departureTime") departureTime: String
    ): Call<Flight>

    @GET("${flights}/getFlightsByArrivalDepartureAndDepartureTimeArrivalTime/")
    fun getFlightsByArrivalDepartureAndDepartureTimeArrivalTime(
        @Query("arrival") arrival: String,
        @Query("departure") departure: String,
        @Query("departureTime") departureTime: String,
        @Query("arrivalTime") arrivalTime: String
    ): Call<Flight>

    @POST("${flights}/addFlight")
    fun addFlight(@Body flight: Flight): Call<Flight>

    @PUT("${flights}/updateFlight")
    fun updateUser(@Query("id") id: Int, @Body flight: Flight): Call<Flight>

    @DELETE("${flights}/deleteFlight")
    fun deleteFlight(@Query("id") id: Int): Call<Flight>
}