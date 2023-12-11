package com.example.flightapp.ui.fragments.settings

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.flightapp.R
import com.example.flightapp.databinding.DialogEditNumberBinding
import com.example.flightapp.databinding.FragmentEmailAndNumberBinding
import com.google.android.material.snackbar.Snackbar


class EmailAndNumberFragment : Fragment() {
    private lateinit var binding : FragmentEmailAndNumberBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEmailAndNumberBinding.inflate(inflater)
        setDialog()
        binding.arrowBack.setOnClickListener {
            findNavController().popBackStack()
        }
        return binding.root
    }


    private fun setDialog(){
        val preference = activity?.getSharedPreferences("UserDetails", Context.MODE_PRIVATE)
        val editor = preference?.edit()
        if(preference!!.contains("phone")){
            val number = preference.getString("phone","No number assigned ")
            binding.txtNumber.text =number
        }
        binding.imgEdit.setOnClickListener {
            val dialogBinding = DialogEditNumberBinding.inflate(layoutInflater)
            val dialogView = dialogBinding.root
            val editTextNumber = dialogBinding.editTextNumber
            val btnUpdateNumber = dialogBinding.btnUpdateNumber

            val builder = AlertDialog.Builder(requireContext())
                .setView(dialogView)

            val dialog = builder.create()

            btnUpdateNumber.setOnClickListener {
                val newNumber = editTextNumber.text.toString()
                binding.txtNumber.text = newNumber
                editor?.putString("phone",newNumber)
                val success = editor?.commit()
                if(success!!){
                    Snackbar.make(requireView(),"Successfully added",Snackbar.LENGTH_SHORT).show()
                }

                dialog.dismiss()
            }

            dialog.show()
        }
}
}