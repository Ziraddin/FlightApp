package com.example.flightapp.ui.fragments.others

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.flightapp.R
import com.example.flightapp.databinding.FragmentBookingDetailsBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class BookingDetailsFragment : Fragment() {
   private lateinit var binding:FragmentBookingDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookingDetailsBinding.inflate(inflater)
        binding.txtEdit.setOnClickListener {
            findNavController().navigate(R.id.action_bookingDetailsFragment_to_contactDetailsFragment)
        }
        binding.arrowBack2.setOnClickListener{
            findNavController().popBackStack()
        }

        binding.imgGoPersonalInfo.setOnClickListener{
            findNavController().navigate(R.id.action_bookingDetailsFragment_to_passengerInfoFragment)
        }

        binding.btnAddBaggage.setOnClickListener{
            val dialog = BottomSheetDialog(requireContext())
            val view = layoutInflater.inflate(R.layout.bottom_sheet_baggage, null)
            dialog.setCancelable(true)
            dialog.setContentView(view)
            dialog.show()
        }


        return binding.root
    }

}