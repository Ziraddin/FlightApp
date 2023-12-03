package com.example.flightapp.ui.fragments.transaction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.flightapp.R
import com.example.flightapp.databinding.FragmentTransactionBinding

class TransactionFragment : Fragment() {
    private lateinit var binding: FragmentTransactionBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTransactionBinding.inflate(inflater)
        setNavigation()
        return binding.root
    }

    private fun setNavigation() {

        binding.btnTransaction.setOnClickListener {
            findNavController().navigate(R.id.action_transactionFragment_to_homeFragment)
        }
    }

}