package com.example.flightapp.api.retrofit.instances

import com.example.flightapp.api.constants.Constants.url
import com.example.flightapp.api.retrofit.interfaces.FlightApi
import com.example.flightapp.api.retrofit.interfaces.PaymentApi
import com.example.flightapp.api.retrofit.interfaces.TransactionApi
import com.example.flightapp.api.retrofit.interfaces.UserApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val userApi: UserApi =
        Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build()
            .create(UserApi::class.java)

    val flightApi: FlightApi =
        Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build()
            .create(FlightApi::class.java)

    val transactionApi: TransactionApi =
        Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build()
            .create(TransactionApi::class.java)

    val paymentApi: PaymentApi =
        Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build()
            .create(PaymentApi::class.java)
}