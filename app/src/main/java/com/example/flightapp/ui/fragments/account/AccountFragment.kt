package com.example.flightapp.ui.fragments.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.flightapp.R
import com.example.flightapp.databinding.FragmentAccountBinding
import com.example.flightapp.databinding.FragmentHomeBinding

class AccountFragment : Fragment() {
   private lateinit var binding:FragmentAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountBinding.inflate(inflater)
        binding.imgGoSettings.setOnClickListener{
            findNavController().navigate(R.id.settingsFragment)
        }

        return binding.root
    }



}