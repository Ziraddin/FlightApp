package com.example.flightapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flightapp.R
import com.example.flightapp.databinding.FragmentSelectSeatBinding
import com.example.flightapp.ui.adapters.recyclerview.SeatAdapters
import com.example.flightapp.ui.adapters.recyclerview.Seats

class SelectSeatFragment : Fragment() {
    private lateinit var binding: FragmentSelectSeatBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSelectSeatBinding.inflate(inflater)
        setNavigation()
        setAdapter()
        return binding.root
    }


    private fun setNavigation() {
        binding.arrowBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setAdapter() {
        val listOfSeats = listOf<Seats>(
            Seats(R.drawable.seat_available),
            Seats(R.drawable.seat_available),
            Seats(R.drawable.seat_available),
            Seats(R.drawable.seat_available),
            Seats(R.drawable.seat_available, notAvailable = true),
            Seats(R.drawable.seat_available),
            Seats(R.drawable.seat_available, notAvailable = true),
            Seats(R.drawable.seat_available),
            Seats(R.drawable.seat_available),
            Seats(R.drawable.seat_available,notAvailable = true),
            Seats(R.drawable.seat_available),
            Seats(R.drawable.seat_available, notAvailable = true),
            Seats(R.drawable.seat_available),
            Seats(R.drawable.seat_available, notAvailable = true),
            Seats(R.drawable.seat_available),
            Seats(R.drawable.seat_available),
            Seats(R.drawable.seat_available),
            Seats(R.drawable.seat_available),
            Seats(R.drawable.seat_available),
            Seats(R.drawable.seat_available, notAvailable = true),
            Seats(R.drawable.seat_available),
            Seats(R.drawable.seat_available, notAvailable = true),
            Seats(R.drawable.seat_available),
            Seats(R.drawable.seat_available)

        )

        val adapter = SeatAdapters(listOfSeats)
        val adapterRight = SeatAdapters(listOfSeats)
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.recyclerView2.layoutManager = GridLayoutManager(requireContext(),3)
        binding.recyclerView.adapter = adapter
        binding.recyclerView2.adapter = adapterRight


    }


}