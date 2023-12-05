package com.example.flightapp.api.retrofit.interfaces

import com.example.flightapp.model.Flight
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface FlightApi {
    @GET("flights/getFlightsByArrivalDepartureAndDepartureTime")
    suspend fun getFlightsByArrivalDepartureAndDepartureTime(
        @Query("arrival") arrival: String,
        @Query("departure") departure: String,
        @Query("departureTime") departureTime: String
    ): Response<List<Flight>>

    @GET("flights/getFlightsByArrivalDepartureAndDepartureTimeArrivalTime")
    suspend fun getFlightsByArrivalDepartureAndDepartureTimeArrivalTime(
        @Query("arrival") arrival: String,
        @Query("departure") departure: String,
        @Query("departureTime") departureTime: String,
        @Query("arrivalTime") arrivalTime: String
    ): Response<List<Flight>>

    @POST("flight}/addFlight")
    suspend fun addFlight(@Body flight: Flight): Response<Flight>

    @PUT("flights/updateFlight")
    suspend fun updateFlight(@Query("id") id: Int, @Body flight: Flight): Response<Flight>

    @DELETE("flights/deleteFlight")
    suspend fun deleteFlight(@Query("id") id: Int): Response<Flight>
}