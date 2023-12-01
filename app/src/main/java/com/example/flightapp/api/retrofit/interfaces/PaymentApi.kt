package com.example.flightapp.api.retrofit.interfaces

import com.example.flightapp.api.constants.Constants.payments
import com.example.flightapp.model.Payment
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface PaymentApi {
    @GET("${payments}/getPaymentById/")
    fun getPaymentById(@Query("id") id: Int): Call<Payment>

    @POST("${payments}/addPaymentType")
    fun addPaymentType(@Body payment: Payment): Call<Payment>

    @PUT("${payments}/updatePaymentType")
    fun updatePaymentType(@Query("id") id: Int, @Body payment: Payment): Call<Payment>

    @DELETE("${payments}/deletePaymentType")
    fun deletePaymentType(@Query("id") id: Int): Call<Payment>
}