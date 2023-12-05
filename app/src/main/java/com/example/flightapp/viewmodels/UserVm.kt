package com.example.flightapp.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flightapp.api.retrofit.instances.RetrofitClient
import com.example.flightapp.model.User
import kotlinx.coroutines.launch

class UserVm : ViewModel() {

    var userLiveData: MutableLiveData<User> = MutableLiveData()
    private val userApiCall = RetrofitClient.userApi

    fun addUser(user: User) {
        viewModelScope.launch {
            val response = userApiCall.createUser(user)
            userLiveData.postValue(response)
        }
    }

    fun getUser(id: Int, context: Context) {
        viewModelScope.launch {
            val response = userApiCall.getUserById(id)
            if (response.isSuccessful) {
                userLiveData.postValue(response.body())
            } else {
                Log.d("UserVm", "getUser: ${response.errorBody()}")
            }
        }
    }

    fun updateUser(id: Int, user: User) {
        viewModelScope.launch {
            val response = userApiCall.updateUser(id, user)
            if (response.isSuccessful) {
                userLiveData.postValue(response.body())
            } else {
                Log.d("UserVm", "updateUser: ${response.errorBody()}")
            }
        }
    }

    fun deleteUser(id: Int) {
        viewModelScope.launch {
            val response = userApiCall.deleteUser(id)
            if (response.isSuccessful) {
                userLiveData.postValue(response.body())
            } else {
                Log.d("UserVm", "deleteUser: ${response.errorBody()}")
            }
        }
    }
}