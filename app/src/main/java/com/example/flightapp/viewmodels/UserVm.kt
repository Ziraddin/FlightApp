package com.example.flightapp.viewmodels

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

    fun getUser(firstname: String, lastname: String, email: String) {
        viewModelScope.launch {
            val response = userApiCall.getUser(firstname, lastname, email)
            userLiveData.postValue(response)
        }
    }

    fun updateUser(id: Int, user: User) {
        viewModelScope.launch {
            Log.d("UserVm", "before api call")
            val response = userApiCall.updateUser(id, user)
            userLiveData.postValue(response)
            Log.d("UserVm", "response $response")
            Log.d("UserVm", "after api call")
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