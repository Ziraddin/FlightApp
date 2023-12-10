
package com.example.flightapp.ui.fragments.others

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.flightapp.R
import com.example.flightapp.databinding.FragmentContactDetailsBinding
import com.google.firebase.auth.FirebaseAuth

class PassengerInfoFragment : Fragment() {
    private lateinit var binding: FragmentContactDetailsBinding
    private lateinit var mAuth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactDetailsBinding.inflate(inflater)
        binding.arrowBack2.setOnClickListener{
            findNavController().popBackStack()
        }
        mAuth = FirebaseAuth.getInstance()
        adaptLayout()
        setLayoutValue()
        return binding.root
    }

    private fun adaptLayout(){
        binding.txtHeader.setText(R.string.txtPassengerInfo)
        binding.cardView3.visibility = View.GONE
        binding.txtFooterInfo.setText(R.string.txtFooterPersonInfo)
    }

    private fun setLayoutValue(){
        binding.txtUsername.text = mAuth.currentUser?.displayName?:"N/A"
        binding.txtEmailAddress.text = mAuth.currentUser?.email ?: "N/A"
        binding.txtPhoneNumber.text = mAuth.currentUser?.phoneNumber ?: "xxx xxx xx xx"
    }
}