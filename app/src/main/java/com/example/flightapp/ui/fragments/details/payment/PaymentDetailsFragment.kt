package com.example.flightapp.ui.fragments.details.payment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.flightapp.R
import com.example.flightapp.databinding.FragmentPaymentDetailsBinding

class PaymentDetailsFragment : Fragment() {
   private lateinit var binding : FragmentPaymentDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPaymentDetailsBinding.inflate(inflater)
        setNavigation()

        return binding.root
    }

    private fun setNavigation(){
        binding.arrowBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnProceed.setOnClickListener {
            findNavController().navigate(R.id.action_paymentDetailsFragment_to_paymentSuccessfulFragment)
        }
    }


}