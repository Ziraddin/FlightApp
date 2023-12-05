package com.example.flightapp.api.retrofit.interfaces

import com.example.flightapp.api.constants.Constants.payments
import com.example.flightapp.model.Payment
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface PaymentApi {
    @GET("${payments}/getPaymentById/")
    suspend fun getPaymentById(@Query("id") id: Int): Payment

    @POST("${payments}/addPaymentType")
    suspend fun addPaymentType(@Body payment: Payment): Payment

    @PUT("${payments}/updatePaymentType")
    suspend fun updatePaymentType(@Query("id") id: Int, @Body payment: Payment): Payment

    @DELETE("${payments}/deletePaymentType")
    suspend fun deletePaymentType(@Query("id") id: Int): Payment
}