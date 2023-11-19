package com.example.flightapp.ui.activities.onboarding.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.flightapp.R
import com.example.flightapp.databinding.FragmentOnBoardingFirstBinding
import com.example.flightapp.databinding.FragmentOnBoardingSecondBinding


class OnBoardingSecondFragment : Fragment() {
    private lateinit var binding : FragmentOnBoardingSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardingSecondBinding.inflate(inflater)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)


        binding.btnNext.setOnClickListener {
            viewPager?.currentItem = 2
        }

        binding.btnSkip.setOnClickListener {
            findNavController().navigate(R.id.toSignInFragment)
        }
        return binding.root
    }

}