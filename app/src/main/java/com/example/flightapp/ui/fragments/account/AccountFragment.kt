package com.example.flightapp.ui.fragments.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.flightapp.R
import com.example.flightapp.databinding.FragmentAccountBinding
import com.facebook.login.LoginManager
import com.google.firebase.auth.FirebaseAuth

class AccountFragment : Fragment() {
    private lateinit var binding: FragmentAccountBinding
    private lateinit var mAuth: FirebaseAuth
    private lateinit var loginManager: LoginManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountBinding.inflate(inflater)
        mAuth = FirebaseAuth.getInstance()
        loginManager = LoginManager.getInstance()

        setUserInfo()
        goBack()
        logout()
        return binding.root
    }


    private fun setUserInfo() {
        binding.txtName.text = mAuth.currentUser?.displayName ?: "no username"
        binding.txtEmail.text = mAuth.currentUser?.email ?: "no email address"
        binding.txtNumber.text = mAuth.currentUser?.phoneNumber ?: "xxx xxx xx xx"
    }

    private fun logout() {
        binding.imgLogOut.setOnClickListener {
            mAuth.signOut()
            loginManager.logOut()
            Toast.makeText(requireContext(), "Signed out successfully", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_accountFragment_to_viewPagerFragment)
        }
    }

    private fun goBack() {
        binding.imgGoSettings.setOnClickListener {
            findNavController().navigate(R.id.action_accountFragment_to_settingsFragment)
        }
    }
}