package com.example.flightapp.api.retrofit.interfaces

import com.example.flightapp.model.Transaction
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface TransactionApi {
    @GET("transactions/getTransactionsByUserId")
    suspend fun getTransactionsByUserId(@Query("userid") userid: Int): List<Transaction?>?

    @POST("transactions/addTransaction")
    suspend fun createTransaction(@Body transaction: Transaction): Transaction

    @PUT("transactions/updateTransaction")
    suspend fun updateTransaction(@Query("id") id: Int, @Body transaction: Transaction): Transaction

    @DELETE("transactions/deleteTransaction")
    suspend fun deleteTransaction(@Query("id") id: Int): Transaction
}