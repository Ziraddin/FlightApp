package com.example.flightapp.ui.fragments.signinup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.flightapp.R
import com.example.flightapp.databinding.FragmentSignInBinding


class SignInFragment : Fragment() {
     private lateinit var binding :FragmentSignInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(inflater)


        setVisibility()

        binding.txtSignUp.setOnClickListener{
            findNavController().navigate(R.id.toSignUpFragment)
        }
        return binding.root
    }

    private fun setVisibility(){
        binding.txtFirstName.visibility = View.GONE
        binding.txtLastName.visibility = View.GONE
        binding.edtFirstName.visibility = View.GONE
        binding.edtLastName.visibility = View.GONE
        binding.arrowBack.visibility = View.INVISIBLE
    }

}