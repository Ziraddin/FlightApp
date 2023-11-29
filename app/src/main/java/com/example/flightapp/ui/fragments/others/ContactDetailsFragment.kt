package com.example.flightapp.ui.fragments.others

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.flightapp.R
import com.example.flightapp.databinding.FragmentContactDetailsBinding


class ContactDetailsFragment : Fragment() {
    private lateinit var binding:FragmentContactDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactDetailsBinding.inflate(inflater)
        binding.arrowBack2.setOnClickListener{
            findNavController().popBackStack()
        }
        return binding.root
    }

}