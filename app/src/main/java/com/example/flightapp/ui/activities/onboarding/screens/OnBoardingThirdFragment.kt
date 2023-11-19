package com.example.flightapp.ui.activities.onboarding.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.example.flightapp.R
import com.example.flightapp.databinding.FragmentOnBoardingSecondBinding
import com.example.flightapp.databinding.FragmentOnBoardingThirdBinding


class OnBoardingThirdFragment : Fragment() {
    private lateinit var binding : FragmentOnBoardingThirdBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardingThirdBinding.inflate(inflater)

        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.toSignInFragment)

        }

        binding.btnSkip.setOnClickListener {
            findNavController().navigate(R.id.toSignInFragment)
        }

        return binding.root
    }

}