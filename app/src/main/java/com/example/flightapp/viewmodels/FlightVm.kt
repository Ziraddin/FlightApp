package com.example.flightapp.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flightapp.api.retrofit.instances.RetrofitClient
import com.example.flightapp.model.Flight
import kotlinx.coroutines.launch

class FlightVm : ViewModel() {

    var flightLiveData: MutableLiveData<List<Flight?>?> = MutableLiveData()
    private val flightApiCall = RetrofitClient.flightApi

    fun getFlightsByArrivalDepartureAndDepartureTime(
        arrival: String, departure: String, departureTime: String
    ) {
        viewModelScope.launch {
            val response = flightApiCall.getFlightsByArrivalDepartureAndDepartureTime(
                arrival, departure, departureTime
            )
            if (response.isSuccessful) {
                flightLiveData.postValue(response.body())
            } else {
                Log.d(
                    "FlightVm", "getFlightsByArrivalDepartureAndDepartureTime: ${
                        response.errorBody().toString()
                    }"
                )
            }
        }
    }

    fun getFlightsByArrivalDepartureAndDepartureTimeArrivalTime(
        arrival: String, departure: String, departureTime: String, arrivalTime: String
    ) {
        viewModelScope.launch {
            val response = flightApiCall.getFlightsByArrivalDepartureAndDepartureTimeArrivalTime(
                arrival, departure, departureTime, arrivalTime
            )
            if (response.isSuccessful) {
                flightLiveData.postValue(response.body())
            } else {
                Log.d(
                    "FlightVm", "getFlightsByArrivalDepartureAndDepartureTimeArrivalTime: ${
                        response.errorBody().toString()
                    }"
                )
            }
        }
    }

    fun updateFlight(id: Int, flight: Flight) {
        viewModelScope.launch {
            val response = flightApiCall.updateFlight(id, flight)
            if (response.isSuccessful) {
                flightLiveData.postValue(listOf(response.body()))
            } else {
                Log.d("FlightVm", "updateFlight: ${response.errorBody().toString()}")
            }
        }
    }

    fun deleteFlight(id: Int) {
        viewModelScope.launch {
            val response = flightApiCall.deleteFlight(id)
            if (response.isSuccessful) {
                flightLiveData.postValue(listOf(response.body()))
            } else {
                Log.d("FlightVm", "deleteFlight: ${response.errorBody().toString()}")
            }
        }
    }

}