package com.example.flightapp.api.retrofit.interfaces

import com.example.flightapp.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface UserApi {
    @GET("users/getUser")
    suspend fun getUserById(@Query("id") id: Int): Response<User>

    @POST("users/addUser")
    suspend fun createUser(@Body user: User): User

    @PUT("users/updateUser")
    suspend fun updateUser(@Query("id") id: Int, @Body user: User): Response<User>

    @DELETE("users/deleteUser")
    suspend fun deleteUser(@Query("id") id: Int): Response<User>
}