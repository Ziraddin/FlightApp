package com.example.flightapp.api.retrofit.interfaces

import com.example.flightapp.api.constants.Constants.transactions
import com.example.flightapp.model.Transaction
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface TransactionApi {
    @GET("${transactions}/getTransactionsByUserId/")
    fun getTransactionsByUserId(@Query("userid") userid: Int): Call<Transaction>

    @POST("${transactions}/addTransaction")
    fun createTransaction(@Body transaction: Transaction): Call<Transaction>

    @PUT("${transactions}/updateTransaction/")
    fun updateTransaction(@Query("id") id: Int, @Body transaction: Transaction): Call<Transaction>

    @DELETE("${transactions}/deleteTransaction")
    fun deleteTransaction(@Query("id") id: Int): Call<Transaction>
}