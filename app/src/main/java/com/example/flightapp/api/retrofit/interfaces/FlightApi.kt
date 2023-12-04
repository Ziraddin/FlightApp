package com.example.flightapp.api.retrofit.interfaces

import com.example.flightapp.api.constants.Constants.flights
import com.example.flightapp.model.Flight
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface FlightApi {
    @GET("${flights}/getFlightsByArrivalDepartureAndDepartureTime")
    suspend fun getFlightsByArrivalDepartureAndDepartureTime(
        @Query("arrival") arrival: String,
        @Query("departure") departure: String,
        @Query("departureTime") departureTime: String
    ): List<Flight>

    @GET("${flights}/getFlightsByArrivalDepartureAndDepartureTimeArrivalTime")
    suspend fun getFlightsByArrivalDepartureAndDepartureTimeArrivalTime(
        @Query("arrival") arrival: String,
        @Query("departure") departure: String,
        @Query("departureTime") departureTime: String,
        @Query("arrivalTime") arrivalTime: String
    ): List<Flight>

    @POST("${flights}/addFlight")
    suspend fun addFlight(@Body flight: Flight): Flight

    @PUT("${flights}/updateFlight")
    suspend fun updateFlight(@Query("id") id: Int, @Body flight: Flight): Flight

    @DELETE("${flights}/deleteFlight")
    suspend fun deleteFlight(@Query("id") id: Int): Flight
}