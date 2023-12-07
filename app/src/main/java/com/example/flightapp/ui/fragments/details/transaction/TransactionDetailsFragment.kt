package com.example.flightapp.ui.fragments.details.transaction

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.flightapp.R
import com.example.flightapp.databinding.FragmentTransactionDetailsBinding
import com.example.flightapp.model.Transaction
import com.example.flightapp.ui.activities.MainActivity

class TransactionDetailsFragment : Fragment() {
    private lateinit var binding: FragmentTransactionDetailsBinding

    override fun onStart() {
        super.onStart()
        val activity = activity as MainActivity
        activity.setBottomNavigation(false)
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTransactionDetailsBinding.inflate(inflater)
        setNavigation()
        setLayoutValue()
        return binding.root
    }


    private fun setNavigation() {
        binding.arrowBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnSave.setOnClickListener {
            findNavController().navigate(R.id.action_transactionDetailsFragment2_to_homeFragment)


        }
    }

    private fun setLayoutValue() {
        val transaction = arguments?.getSerializable("transaction") as? Transaction

        transaction?.let {
            val user = it.user
            val flight = it.flight

            with(binding) {
                txtContactName.text = "${user?.firstname} ${user?.lastname}"
                txtCompanyName.text = flight?.company
                txtUserName.text = "${user?.firstname} ${user?.lastname}"
                txtPrice1.text = (flight?.price ?: 0).toString()
                txtPrice2.text = (flight?.price ?: 0).toString()
                txtTakeOffTime.text = flight?.departureTime
                txtLandTime.text = flight?.arrivalTime
                txtTransactionDate.text = it.date
                txtInvoice.text = it.id.toString()
                txtPaymentMethod.text = user?.payment.toString()
            }
        }
    }


}