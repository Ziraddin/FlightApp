package com.example.flightapp.ui.fragments.transaction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.flightapp.R
import com.example.flightapp.databinding.FragmentTransactionDetailsBinding

class TransactionDetailsFragment : Fragment() {
    private lateinit var binding: FragmentTransactionDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTransactionDetailsBinding.inflate(inflater)
        setNavigation()
        return binding.root
    }


    private fun setNavigation(){
        binding.arrowBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnSave.setOnClickListener {
            findNavController().navigate(R.id.action_transactionDetailsFragment_to_homeFragment)


        }
    }

}