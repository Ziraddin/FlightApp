package com.example.flightapp.ui.fragments.details.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.flightapp.R
import com.example.flightapp.api.constants.Constants.cUser
import com.example.flightapp.databinding.FragmentPaymentDetailsBinding
import com.example.flightapp.model.User
import com.example.flightapp.ui.fragments.details.booking.BookingDetailsFragment
import com.example.flightapp.viewmodels.UserVm

class PaymentDetailsFragment : Fragment() {
    private lateinit var binding: FragmentPaymentDetailsBinding
    private lateinit var userVm: UserVm
    private lateinit var user: User

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentPaymentDetailsBinding.inflate(inflater)
        userVm = ViewModelProvider(this)[UserVm::class.java]
        setLayoutValue()
        userVm.getUser(
            cUser.firstname!!, cUser.lastname!!, cUser.email!!
        )

        userVm.userLiveData.observe(viewLifecycleOwner, Observer {
            user = it
            setupRadioGroup()
        })

        setNavigation()
        return binding.root
    }

    private fun setupRadioGroup() {
        binding.radioGroup3.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.MasterCard -> {
                    user.payment?.id = 1
                    updateUserAndEnableButton()
                }

                R.id.PayPal -> {
                    user.payment?.id = 2
                    updateUserAndEnableButton()
                }
            }
        }
    }

    private fun updateUserAndEnableButton() {
        userVm.updateUser(user.id!!, user)
        userVm.userLiveData.observe(viewLifecycleOwner, Observer {
            user = it
            binding.btnProceed.isEnabled = user.payment?.id != null
        })
    }

    private fun setNavigation() {
        binding.arrowBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnProceed.setOnClickListener {
            findNavController().navigate(R.id.action_paymentDetailsFragment_to_paymentSuccessfulFragment)
        }
    }

    private fun setLayoutValue(){
        binding.txtTotalPrice.text = BookingDetailsFragment.priceTotal.toString()
    }
}