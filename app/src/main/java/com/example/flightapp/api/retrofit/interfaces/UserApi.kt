package com.example.flightapp.api.retrofit.interfaces

import com.example.flightapp.api.constants.Constants.users
import com.example.flightapp.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface UserApi {
    @GET("$users/getUser/")
    fun getUserById(@Query("id") id: Int): Call<User>

    @POST("$users/addUser")
    fun createUser(@Body user: User): Call<User>

    @PUT("$users/updateUser")
    fun updateUser(@Query("id") id: Int, @Body user: User): Call<User>

    @DELETE("$users/deleteUser")
    fun deleteUser(@Query("id") id: Int): Call<User>
}