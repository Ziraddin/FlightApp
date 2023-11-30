package com.example.flightapp.ui.fragments.account

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.flightapp.R
import com.example.flightapp.databinding.FragmentAccountBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

class AccountFragment : Fragment() {
    private lateinit var binding: FragmentAccountBinding
    private lateinit var mAuth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountBinding.inflate(inflater)
        mAuth = FirebaseAuth.getInstance()
        binding.imgGoSettings.setOnClickListener {
            findNavController().navigate(R.id.action_accountFragment_to_settingsFragment)
        }



        binding.imgLogOut.setOnClickListener {
            mAuth.signOut()
            Toast.makeText(requireContext(), "Signed out successfully", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_accountFragment_to_viewPagerFragment)
        }


        return binding.root
    }

}