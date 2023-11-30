package com.example.flightapp.ui.fragments.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.flightapp.R

import com.example.flightapp.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
   private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater)
        setNavigation()

        return binding.root
    }

    private fun setNavigation(){
        binding.arrowBack.setOnClickListener{
            findNavController().popBackStack()
        }

        binding.imgGoLanguage.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_languageSettingsFragment)
        }

        binding.imgGoEmail.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_emailAndNumberFragment)
        }

        binding.imgGoDelete.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_deleteAccountFragment)
        }
    }
}