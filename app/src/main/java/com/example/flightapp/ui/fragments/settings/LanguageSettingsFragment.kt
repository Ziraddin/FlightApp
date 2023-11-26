package com.example.flightapp.ui.fragments.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.flightapp.R
import com.example.flightapp.databinding.FragmentLanguageSettingsBinding
import com.example.flightapp.databinding.FragmentSettingsBinding

class LanguageSettingsFragment : Fragment() {
    private lateinit var binding: FragmentLanguageSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLanguageSettingsBinding.inflate(inflater)
        setNavigation()

        return binding.root
    }


    private fun setNavigation(){
        binding.arrowBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}