package com.example.flightapp.ui.fragments.home.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flightapp.R
import com.example.flightapp.databinding.FragmentSearchFlightsBinding
import com.example.flightapp.model.Flight
import com.example.flightapp.ui.activities.MainActivity
import com.example.flightapp.ui.adapters.recyclerview.FlightTicketAdapter
import com.example.flightapp.viewmodels.FlightVm

class SearchFlights : Fragment() {
    private lateinit var binding: FragmentSearchFlightsBinding
    private lateinit var flightVm: FlightVm
    private lateinit var adapter: FlightTicketAdapter
    private var isDataFetched = false
    private var flightTickets = mutableListOf<Flight>()

    override fun onStart() {
        super.onStart()
        val activity = requireActivity() as? MainActivity
        activity?.setBottomNavigation(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
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
        adapter = FlightTicketAdapter(mutableListOf()) {
            findNavController().navigate(R.id.action_searchFlights_to_bookingDetailsFragment)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        if (!isDataFetched) {
            val searchedDestinationFrom: String? = arguments?.getString("destinationFrom")
            val searchedDestinationTo: String? = arguments?.getString("destinationTo")
            val departureDate: String? = arguments?.getString("departureDate")
            val returnDate: String? = arguments?.getString("returnDate")

            if (!returnDate.isNullOrBlank()) {
                flightVm.getFlightsByArrivalDepartureAndDepartureTime(
                    searchedDestinationTo!!, searchedDestinationFrom!!, departureDate!!
                )
                flightVm.getFlightsByArrivalDepartureAndDepartureTime(
                    searchedDestinationFrom, searchedDestinationTo, returnDate
                )
            } else {
                flightVm.getFlightsByArrivalDepartureAndDepartureTime(
                    searchedDestinationTo!!, searchedDestinationFrom!!, departureDate!!
                )
            }

            flightVm.flightLiveData.observe(viewLifecycleOwner, Observer {
                flightTickets = it as MutableList<Flight>
                Log.d("FlightVm", "setAdapter: $flightTickets")

                if (flightTickets.isNotEmpty()) {
                    adapter.addData(flightTickets)
                    binding.recyclerView.visibility = View.VISIBLE
                    binding.textView7.visibility = View.INVISIBLE
                } else {
                    showNoResultsMessage()
                }
            })
            isDataFetched = true
        } else {
            adapter.updateData(flightTickets)
        }
    }

    private fun showNoResultsMessage() {
        binding.recyclerView.visibility = View.INVISIBLE
        binding.textView7.visibility = View.VISIBLE
    }

}