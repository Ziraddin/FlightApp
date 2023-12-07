package com.example.flightapp.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flightapp.api.retrofit.instances.RetrofitClient
import com.example.flightapp.model.Transaction
import kotlinx.coroutines.launch

class TransactionVm : ViewModel() {

    var transactionsLiveData = MutableLiveData<List<Transaction?>?>()
    private val transactionApi = RetrofitClient.transactionApi

    fun getTransactionsByUserId(userid: Int) {
        viewModelScope.launch {
            Log.d("TransactionVm", "Before Api Call")
            val response = transactionApi.getTransactionsByUserId(userid)
            transactionsLiveData.value = response
            Log.d("TransactionVm", "getTransactionsByUserId: $response")
            Log.d("TransactionVm", "After Api Call")
        }
    }

    fun createTransaction(transaction: Transaction) {
        viewModelScope.launch {
            Log.d("TransactionVm", "Before Api Call")
            val response = transactionApi.createTransaction(transaction)
            Log.d("TransactionVm", "createTransaction: $response")
            Log.d("TransactionVm", "After Api Call")
        }
    }

    fun updateTransaction(id: Int, transaction: Transaction) {
        viewModelScope.launch {
            Log.d("TransactionVm", "Before Api Call")
            val response = transactionApi.updateTransaction(id, transaction)
            Log.d("TransactionVm", "updateTransaction: $response")
            Log.d("TransactionVm", "After Api Call")
        }
    }

    fun deleteTransaction(id: Int) {
        viewModelScope.launch {
            Log.d("TransactionVm", "Before Api Call")
            val response = transactionApi.deleteTransaction(id)
            Log.d("TransactionVm", "deleteTransaction: $response")
            Log.d("TransactionVm", "After Api Call")
        }
    }
}