package com.example.flightapp.ui.fragments.signinup

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.flightapp.R
import com.example.flightapp.databinding.FragmentEmailVerificationBinding
import com.google.firebase.auth.FirebaseAuth

class EmailVerificationFragment : Fragment() {
    lateinit var binding: FragmentEmailVerificationBinding
    private lateinit var mAuth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentEmailVerificationBinding.inflate(inflater)
        mAuth = FirebaseAuth.getInstance()
        val user = mAuth.currentUser

        Handler(Looper.getMainLooper()).postDelayed({
            if (user?.isEmailVerified == true) {
                findNavController().navigate(R.id.action_emailVerificationFragment_to_homeFragment)
            }
        }, 4500)

        return binding.root
    }
}