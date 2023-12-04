package com.example.flightapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flightapp.api.retrofit.instances.RetrofitClient
import com.example.flightapp.model.Flight
import kotlinx.coroutines.launch

class FlightVm : ViewModel() {

    var flightLiveData: MutableLiveData<List<Flight>> = MutableLiveData()
    private val flightApiCall = RetrofitClient.flightApi

    fun getFlightsByArrivalDepartureAndDepartureTime(
        arrival: String, departure: String, departureTime: String
    ) {
        viewModelScope.launch {
            flightLiveData.postValue(
                flightApiCall.getFlightsByArrivalDepartureAndDepartureTime(
                    arrival, departure, departureTime
                )
            )
        }
    }

    fun getFlightsByArrivalDepartureAndDepartureTimeArrivalTime(
        arrival: String, departure: String, departureTime: String, arrivalTime: String
    ) {
        viewModelScope.launch {
            flightLiveData.postValue(
                flightApiCall.getFlightsByArrivalDepartureAndDepartureTimeArrivalTime(
                    arrival, departure, departureTime, arrivalTime
                )
            )
        }
    }

    fun updateFlight(id: Int, flight: Flight) {
        viewModelScope.launch {
            flightApiCall.updateFlight(id, flight)
        }
    }

    fun deleteFlight(id: Int) {
        viewModelScope.launch {
            flightApiCall.deleteFlight(id)
        }
    }

}