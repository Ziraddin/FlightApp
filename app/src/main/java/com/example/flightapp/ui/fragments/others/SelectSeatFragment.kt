package com.example.flightapp.ui.fragments.others

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flightapp.R
import com.example.flightapp.databinding.FragmentSelectSeatBinding
import com.example.flightapp.ui.adapters.recyclerview.SeatAdapters
import com.example.flightapp.ui.adapters.recyclerview.Seats
import com.example.flightapp.ui.adapters.recyclerview.SelectedSeatAdapter

class SelectSeatFragment : Fragment() {
    private lateinit var binding: FragmentSelectSeatBinding
    private var adapterSelected: SelectedSeatAdapter? = null
    private var adapter: SeatAdapters? = null
    private var adapterRight: SeatAdapters? = null


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

        binding.btnSelectSeat.setOnClickListener {
            findNavController().navigate(R.id.action_selectSeatFragment_to_paymentDetailsFragment)
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
            Seats(R.drawable.seat_available, notAvailable = true),
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

        adapter = SeatAdapters(listOfSeats)
        adapterRight = SeatAdapters(listOfSeats)
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.recyclerView2.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.recyclerView.adapter = adapter
        binding.recyclerView2.adapter = adapterRight

        val selectedSeats = ArrayList<Seats>()

        adapterSelected = SelectedSeatAdapter(selectedSeats )
        binding.recyclerView3.layoutManager = GridLayoutManager(requireContext(), 4)
        binding.recyclerView3.adapter = adapterSelected
        adapter?.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                val selectedSeats = adapter?.getSelectedSeats()
                adapterSelected?.updateSelectedSeats(selectedSeats ?: emptyList())
            }
        })

        adapterRight?.registerAdapterDataObserver(object :RecyclerView.AdapterDataObserver(){
            override fun onChanged() {
                val selectedSeats = adapterRight?.getSelectedSeats()
                adapterSelected?.updateSelectedSeats(selectedSeats?: emptyList())
            }
        })
    }


}