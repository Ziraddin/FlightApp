package com.example.flightapp.ui.fragments.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.flightapp.R
import com.example.flightapp.databinding.FragmentDeleteAccountBinding
import com.example.flightapp.viewmodels.UserVm
import com.google.firebase.auth.FirebaseAuth

class DeleteAccountFragment : Fragment() {
    private lateinit var binding: FragmentDeleteAccountBinding
    lateinit var userVm: UserVm
    lateinit var mAuth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentDeleteAccountBinding.inflate(inflater)
        mAuth = FirebaseAuth.getInstance()
        userVm = ViewModelProvider(this)[UserVm::class.java]
        setNavigation()
        binding.checkBoxDeleteAccount.setOnCheckedChangeListener { _, isChecked ->
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
            binding.btnContinue.setOnClickListener {
                deleteUser()
            }
        } else {
            binding.btnContinue.isEnabled = false
            binding.btnContinue.isClickable = false
            ContextCompat.getDrawable(requireContext(), R.drawable.btn_disabled)?.let {
                binding.btnContinue.background = it
            }
        }
    }

    private fun deleteUser() {
        mAuth.currentUser?.delete()?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                userVm.deleteUser(18)
                mAuth.signOut()
                Toast.makeText(
                    requireContext(),
                    "Your account has been deleted successfully.",
                    Toast.LENGTH_SHORT
                ).show()
                findNavController().navigate(R.id.action_deleteAccountFragment_to_viewPagerFragment)
            } else {
                Toast.makeText(
                    requireContext(),
                    "Something went wrong. Please try again later.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun setNavigation() {
        binding.arrowBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}


