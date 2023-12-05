package com.example.flightapp.ui.fragments.home.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flightapp.R
import com.example.flightapp.databinding.FragmentSearchFlightsBinding
import com.example.flightapp.ui.activities.MainActivity
import com.example.flightapp.ui.adapters.recyclerview.Flight
import com.example.flightapp.ui.adapters.recyclerview.FlightTicketAdapter
import com.example.flightapp.viewmodels.FlightVm
import java.util.Locale

class SearchFlights : Fragment() {
    private lateinit var binding: FragmentSearchFlightsBinding
    private lateinit var flightVm: FlightVm

    override fun onStart() {
        super.onStart()
        val activity = requireActivity() as? MainActivity
        activity?.setBottomNavigation(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchFlightsBinding.inflate(inflater)
        flightVm = ViewModelProvider(this)[FlightVm::class.java]
        setAdapter()

        binding.arrowBack.setOnClickListener {
            findNavController().popBackStack()
        }
        return binding.root
    }

    private fun setAdapter() {
        val searchedDestinationFrom: String? = arguments?.getString("destinationFrom")
        val searchedDestinationTo: String? = arguments?.getString("destinationTo")
        val searchedDate: String? = arguments?.getString("date")

        val flightTickets = mutableListOf<Flight>()

        flightVm.getFlightsByArrivalDepartureAndDepartureTime(
            searchedDestinationFrom!!,
            searchedDestinationTo!!,
            searchedDate!!
        )
        flightVm.flightLiveData.observe(viewLifecycleOwner, Observer {
            flightTickets
        })


        if (flightTickets.isNotEmpty()) {
            setupRecyclerView(flightTickets)
        } else {
            showNoResultsMessage()
        }

    }

    private fun setupRecyclerView(searchedFlights: List<Flight>) {
        val adapter = FlightTicketAdapter(searchedFlights) {
            findNavController().navigate(R.id.action_searchFlights_to_bookingDetailsFragment)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    private fun showNoResultsMessage() {
        binding.recyclerView.visibility = View.GONE
        binding.textView7.visibility = View.VISIBLE
    }

}