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

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun setLayoutValue(){
        arguments?.let {
            val transaction = it.getSerializable("transaction", Transaction::class.java)
            binding.txtContactName.text = transaction?.user?.firstname + " " + transaction?.user?.lastname
            binding.txtCompanyName.text = transaction?.flight?.company
            binding.txtUserName.text = transaction?.user?.firstname + " " + transaction?.user?.lastname
            binding.txtPrice1.text = (transaction?.flight?.price).toString()
            binding.txtPrice2.text = (transaction?.flight?.price).toString()
            binding.txtTakeOffTime.text = transaction?.flight?.arrivalTime
            binding.txtLandTime.text = transaction?.flight?.arrivalTime
            binding.txtTransactionDate.text = transaction?.date
            binding.txtInvoice.text = transaction?.id.toString()
            binding.txtPaymentMethod.text = transaction?.user?.payment.toString()
        }
    }

}