package com.example.flightapp.ui.fragments.transaction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flightapp.R
import com.example.flightapp.api.constants.Constants.cUser
import com.example.flightapp.databinding.FragmentTransactionBinding
import com.example.flightapp.model.Transaction
import com.example.flightapp.model.User
import com.example.flightapp.ui.activities.MainActivity
import com.example.flightapp.ui.adapters.recyclerview.TransactionAdapter
import com.example.flightapp.viewmodels.TransactionVm
import com.example.flightapp.viewmodels.UserVm

class TransactionFragment : Fragment() {
    private lateinit var binding: FragmentTransactionBinding
    private lateinit var adapter: TransactionAdapter
    private lateinit var transactionVm: TransactionVm
    private lateinit var userVm: UserVm
    private lateinit var user: User

    override fun onStart() {
        super.onStart()
        val activity = activity as MainActivity
        activity.setBottomNavigation(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentTransactionBinding.inflate(inflater)
        userVm = ViewModelProvider(this)[UserVm::class.java]
        transactionVm = ViewModelProvider(this)[TransactionVm::class.java]

        setRecyclerView()
        binding.progressBar.visibility = View.VISIBLE
        userVm.getUser(cUser.firstname!!, cUser.lastname!!, cUser.email!!)

        userVm.userLiveData.observe(viewLifecycleOwner, Observer {
            user = it
            transactionVm.getTransactionsByUserId(user.id!!)
        })

        transactionVm.transactionsLiveData.observe(viewLifecycleOwner, Observer {
            val data = it as MutableList<Transaction>
            adapter.updateData(data)
            binding.progressBar.visibility = View.GONE
            setVisibility()
        })

        setNavigation()
        return binding.root

    }

    private fun setVisibility() {
        if (adapter.itemCount == 0) {
            binding.rvTransaction.visibility = View.GONE
            binding.imgTransaction.visibility = View.VISIBLE
            binding.txtTransactionHeader.visibility = View.VISIBLE
            binding.txtTransactionDescription.visibility = View.VISIBLE
            binding.btnTransaction.visibility = View.VISIBLE
        } else {
            binding.rvTransaction.visibility = View.VISIBLE
            binding.imgTransaction.visibility = View.GONE
            binding.txtTransactionHeader.visibility = View.GONE
            binding.txtTransactionDescription.visibility = View.GONE
            binding.btnTransaction.visibility = View.GONE
        }
    }


    private fun setRecyclerView() {
        adapter = TransactionAdapter(mutableListOf()) {
            findNavController().navigate(R.id.action_transactionFragment_to_transactionDetailsFragment2,it)
        }

        binding.rvTransaction.layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.VERTICAL, false
        )
        binding.rvTransaction.adapter = adapter
    }

    private fun setNavigation() {
        binding.btnTransaction.setOnClickListener {
            findNavController().navigate(R.id.action_transactionFragment_to_homeFragment)
        }
    }

}