package com.example.flightapp.ui.fragments.details.contact

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.flightapp.R
import com.example.flightapp.databinding.FragmentContactDetailsBinding
import com.google.firebase.auth.FirebaseAuth


class ContactDetailsFragment : Fragment() {
    private lateinit var binding:FragmentContactDetailsBinding
    private lateinit var mAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactDetailsBinding.inflate(inflater)
        mAuth = FirebaseAuth.getInstance()
        binding.arrowBack2.setOnClickListener{
            findNavController().popBackStack()
        }
        setLayoutValue()
        return binding.root
    }


    private fun setLayoutValue(){
        binding.txtUsername.text = mAuth.currentUser?.displayName?:"N/A"
        binding.txtEmailAddress.text = mAuth.currentUser?.email ?: "N/A"
        binding.txtPhoneNumber.text = activity?.getSharedPreferences("UserDetails", Context.MODE_PRIVATE)?.getString("phone","No number assigned")
    }
}