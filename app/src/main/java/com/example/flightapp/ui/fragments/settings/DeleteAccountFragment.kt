package com.example.flightapp.ui.fragments.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.flightapp.R
import com.example.flightapp.databinding.FragmentDeleteAccountBinding
import com.example.flightapp.databinding.FragmentEmailAndNumberBinding

class DeleteAccountFragment : Fragment() {
    private lateinit var binding: FragmentDeleteAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDeleteAccountBinding.inflate(inflater)

        setNavigation()
        binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
            setButton(isChecked)
        }
        return binding.root
    }


    private fun setButton(isChecked: Boolean) {
        if (isChecked) {
            binding.btnContinue.isEnabled = true
            binding.btnContinue.isClickable = true
            ContextCompat.getDrawable(requireContext(), R.drawable.btn_next)?.let {
                binding.btnContinue.background = it
            }
        } else {
            binding.btnContinue.isEnabled = false
            binding.btnContinue.isClickable = false
            ContextCompat.getDrawable(requireContext(), R.drawable.btn_disabled)?.let {
                binding.btnContinue.background = it
            }
        }
    }

    private fun setNavigation() {
        binding.arrowBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }


}


