package com.example.flightapp.ui.fragments.payment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.flightapp.R
import com.example.flightapp.databinding.FragmentPaymentSuccessfulBinding

class PaymentSuccessfulFragment : Fragment() {
    private lateinit var binding:FragmentPaymentSuccessfulBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPaymentSuccessfulBinding.inflate(inflater)
        setNavigation()
        return binding.root
    }

    private fun setNavigation(){
        binding.btnSeeDetails.setOnClickListener {
            findNavController().navigate(R.id.action_paymentSuccessfulFragment_to_transactionDetailsFragment)
        }
    }
}