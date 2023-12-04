package com.example.flightapp.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flightapp.api.retrofit.instances.RetrofitClient
import com.example.flightapp.model.User
import kotlinx.coroutines.launch

class UserVm() : ViewModel() {

    private var userLiveData: MutableLiveData<User> = MutableLiveData()
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
            userLiveData.postValue(response)
        }
    }

    fun updateUser(id: Int, user: User) {
        viewModelScope.launch {
            val response = userApiCall.updateUser(id, user)
            userLiveData.postValue(response)
        }
    }

    fun deleteUser(id: Int) {
        viewModelScope.launch {
            val response = userApiCall.deleteUser(id)
            userLiveData.postValue(response)
        }
    }
}