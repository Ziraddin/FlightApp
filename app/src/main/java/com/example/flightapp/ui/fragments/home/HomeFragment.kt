package com.example.flightapp.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.flightapp.R
import com.example.flightapp.databinding.FragmentHomeBinding
import com.example.flightapp.ui.activities.MainActivity
import com.example.flightapp.viewmodels.FlightVm
import com.google.android.material.bottomsheet.BottomSheetDialog

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    lateinit var flightVm: FlightVm
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        flightVm = ViewModelProvider(this)[FlightVm::class.java]
        setDateListener()
        btnClickListener()
        setLayout()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireActivity() as? MainActivity
        activity?.setBottomNavigation(true)
    }


    private fun setDateListener() {
        binding.edtDeparture.setOnClickListener {
            setDate(binding.edtDeparture)
        }

        binding.edtReturn.setOnClickListener {
            setDate(binding.edtReturn)
        }
    }

    private fun setDate(editText: EditText) {
        val dialog = BottomSheetDialog(requireContext())
        val view = layoutInflater.inflate(R.layout.bottom_sheet_calendar, null)
        dialog.setCancelable(true)
        dialog.setContentView(view)
        val calendarView = view.findViewById<CalendarView>(R.id.calendarView)
        val btnSelect = view.findViewById<Button>(R.id.btnSelect)
        var selectedDate = ""
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            selectedDate = if (dayOfMonth < 10) {
                "$year-${month + 1}-0$dayOfMonth"
            } else {
                "$year-${month + 1}-$dayOfMonth"
            }
            view.findViewById<TextView>(R.id.txtDate).text = selectedDate
        }
        btnSelect.setOnClickListener {
            editText.setText(selectedDate)
            dialog.hide()
        }
        dialog.show()
    }


    private fun btnClickListener() {
        binding.btnSearch.setOnClickListener {
            val bundle = Bundle()
            val destinationFrom = binding.edtFrom.text.toString()
            val destinationTo = binding.edtTo.text.toString()
            val departureDate = binding.edtDeparture.text.toString()

            if (binding.radioGroup.checkedRadioButtonId == R.id.radioBtnRound) {
                val returnDate = binding.edtReturn.text.toString()
                bundle.putString("returnDate", returnDate)
            }

            bundle.putString("destinationFrom", destinationFrom)
            bundle.putString("destinationTo", destinationTo)
            bundle.putString("departureDate", departureDate)

            findNavController().navigate(R.id.action_homeFragment_to_searchFlights, bundle)
        }
    }


    private fun setLayout() {
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radioBtnOneWay -> {
                    binding.edtReturn.visibility = View.GONE
                    binding.txtReturn.visibility = View.GONE
                }

                R.id.radioBtnRound -> {
                    binding.edtReturn.visibility = View.VISIBLE
                    binding.txtReturn.visibility = View.VISIBLE
                }
            }
        }
    }

}