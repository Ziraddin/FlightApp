package com.example.flightapp.ui.fragments.home

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import android.widget.DatePicker
import android.widget.TextView
import androidx.core.os.BundleCompat
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.flightapp.R
import com.example.flightapp.databinding.FragmentHomeBinding
import com.example.flightapp.ui.activities.MainActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.NonDisposableHandle.parent

class HomeFragment : Fragment() {
    private lateinit var binding:FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentHomeBinding.inflate(inflater)
        setDate()
        btnClickListener()


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireActivity() as? MainActivity
        activity?.setBottomNavigation(true)
    }


    private fun setDate() {
        binding.edtDeparture.setOnClickListener {
            val dialog = BottomSheetDialog(requireContext())
            val view = layoutInflater.inflate(R.layout.bottom_sheet_calendar, null)
            dialog.setCancelable(true)
            dialog.setContentView(view)
            val calendarView = view.findViewById<CalendarView>(R.id.calendarView)
            val btnSelect = view.findViewById<Button>(R.id.btnSelect)
            var selectedDate = ""
            calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
                selectedDate = "$dayOfMonth/${month + 1}/$year"
                view.findViewById<TextView>(R.id.txtDate).text = selectedDate

            }
            btnSelect.setOnClickListener{
                binding.edtDeparture.setText(selectedDate)
                dialog.hide()
            }
            dialog.show()
        }
    }


    private fun btnClickListener(){
        binding.btnSearch.setOnClickListener {
            val destinationFrom = binding.edtFrom.text.toString()
            val destinationTo = binding.edtTo.text.toString()
            val date = binding.edtDeparture.text.toString()
            val bundle = Bundle()
            bundle.putString("destinationFrom",destinationFrom)
            bundle.putString("destinationTo",destinationTo)
            bundle.putString("date",date)
            findNavController().navigate(R.id.action_homeFragment_to_searchFlights,bundle)
        }
    }

}