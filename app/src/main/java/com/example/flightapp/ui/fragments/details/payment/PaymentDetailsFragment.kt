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
import com.example.flightapp.model.Transaction
import com.example.flightapp.model.User
import com.example.flightapp.viewmodels.TransactionVm
import com.example.flightapp.viewmodels.UserVm
import kotlinx.coroutines.delay

class PaymentDetailsFragment : Fragment() {
    private lateinit var binding: FragmentPaymentDetailsBinding
    private lateinit var userVm: UserVm
    private lateinit var user: User
    private lateinit var transaction: Transaction
    lateinit var transactionVm: TransactionVm

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentPaymentDetailsBinding.inflate(inflater)
        userVm = ViewModelProvider(this)[UserVm::class.java]
        transactionVm = ViewModelProvider(this)[TransactionVm::class.java]

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
            binding.btnProceed.isEnabled = true
        })
    }

    private fun setNavigation() {
        binding.arrowBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnProceed.setOnClickListener {
            arguments?.let {
                transaction = it.getSerializable("transaction") as Transaction
                transactionVm.createTransaction(transaction)
            }
            findNavController().navigate(R.id.action_paymentDetailsFragment_to_paymentSuccessfulFragment)
        }
    }
}