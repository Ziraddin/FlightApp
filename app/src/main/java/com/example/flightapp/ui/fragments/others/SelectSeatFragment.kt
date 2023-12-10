package com.example.flightapp.ui.fragments.others

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flightapp.R
import com.example.flightapp.api.constants.Constants
import com.example.flightapp.databinding.FragmentSelectSeatBinding
import com.example.flightapp.model.Flight
import com.example.flightapp.model.Transaction
import com.example.flightapp.model.User
import com.example.flightapp.ui.adapters.recyclerview.SeatAdapters
import com.example.flightapp.ui.adapters.recyclerview.Seats
import com.example.flightapp.ui.adapters.recyclerview.SelectedSeatAdapter
import com.example.flightapp.ui.fragments.details.booking.BookingDetailsFragment
import com.example.flightapp.viewmodels.UserVm
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class SelectSeatFragment : Fragment() {
    private lateinit var binding: FragmentSelectSeatBinding
    private var adapterSelected: SelectedSeatAdapter? = null
    private var adapter: SeatAdapters? = null
    private var adapterRight: SeatAdapters? = null
    private var seatNo: String? = null
    private lateinit var userVm: UserVm
    private lateinit var user: User


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSelectSeatBinding.inflate(inflater)
        setNavigation()
        setAdapter()
        return binding.root
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun setNavigation() {
        binding.arrowBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnSelectSeat.setOnClickListener {
            val flight = arguments?.getSerializable("flight") as? Flight
            val currentDate = getCurrentDate()
            val baggage = BookingDetailsFragment.baggage.toString()
            userVm = ViewModelProvider(this)[UserVm::class.java]
            userVm.getUser(
                Constants.cUser.firstname!!,
                Constants.cUser.lastname!!,
                Constants.cUser.email!!
            )
            userVm.userLiveData.observe(viewLifecycleOwner, Observer {
                user = it
            })
            val transaction = Transaction(
                0,
                date = currentDate,
                baggage = baggage,
                seatNumber = seatNo,
                flight = flight!!,
                user = user
            )
            val bundle = Bundle()
            bundle.putSerializable("transaction", transaction)
            findNavController().navigate(
                R.id.action_selectSeatFragment_to_paymentDetailsFragment,
                bundle
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getCurrentDate(): String {
        val currentDate = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return currentDate.format(formatter)
    }

    private fun setAdapter() {
        val listOfSeats = mutableListOf(
            Seats(R.drawable.seat_available, "A1"),
            Seats(R.drawable.seat_available, "A2"),
            Seats(R.drawable.seat_available, "A3"),
            Seats(R.drawable.seat_available, "A4"),
            Seats(R.drawable.seat_available, "A5", notAvailable = true),
            Seats(R.drawable.seat_available, "A6"),
            Seats(R.drawable.seat_available, "A7", notAvailable = true),
            Seats(R.drawable.seat_available, "A8"),
            Seats(R.drawable.seat_available, "A9"),
            Seats(R.drawable.seat_available, "A10", notAvailable = true),
            Seats(R.drawable.seat_available, "B1"),
            Seats(R.drawable.seat_available, "B2", notAvailable = true),
            Seats(R.drawable.seat_available, "B3"),
            Seats(R.drawable.seat_available, "B4", notAvailable = true),
            Seats(R.drawable.seat_available, "B5"),
            Seats(R.drawable.seat_available, "B6"),
            Seats(R.drawable.seat_available, "B7"),
            Seats(R.drawable.seat_available, "B8"),
            Seats(R.drawable.seat_available, "B9"),
            Seats(R.drawable.seat_available, "B10", notAvailable = true),
            Seats(R.drawable.seat_available, "C1"),
            Seats(R.drawable.seat_available, "C2", notAvailable = true),
            Seats(R.drawable.seat_available, "C3"),
            Seats(R.drawable.seat_available, "C4")
        )

        adapter = SeatAdapters(listOfSeats)
        adapterRight = SeatAdapters(listOfSeats)
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.recyclerView2.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.recyclerView.adapter = adapter
        binding.recyclerView2.adapter = adapterRight

        val selectedSeats = ArrayList<Seats>()

        adapterSelected = SelectedSeatAdapter(selectedSeats)
        binding.recyclerView3.layoutManager = GridLayoutManager(requireContext(), 4)
        binding.recyclerView3.adapter = adapterSelected
        adapter?.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                val selectedSeats = adapter?.getSelectedSeats()

                adapterSelected?.updateSelectedSeats(selectedSeats ?: emptyList())
                seatNo = selectedSeats!!.last().seatNumber
            }
        })

        adapterRight?.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                val selectedSeats = adapterRight?.getSelectedSeats()

                adapterSelected?.updateSelectedSeats(selectedSeats ?: emptyList())

            }
        })
    }


}