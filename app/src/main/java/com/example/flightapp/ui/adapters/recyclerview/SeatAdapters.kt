package com.example.flightapp.ui.adapters.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flightapp.R
import com.example.flightapp.databinding.ListFlightSeatsBinding

class SeatAdapters(private val listSeats: List<Seats>) :
    RecyclerView.Adapter<SeatAdapters.ViewHolder>() {
    private var selectedSeats: MutableList<Seats> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            ListFlightSeatsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listSeats.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = listSeats[position]
        return holder.bind(current)
    }

    inner class ViewHolder(private val binding: ListFlightSeatsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(seat: Seats) {

            binding.imgSeat.setImageResource(if (seat.isSelected) R.drawable.seat_selected else if (seat.notAvailable) R.drawable.seat_notavailable else R.drawable.seat_available)
            binding.imgSeat.setOnClickListener {
                if (!seat.notAvailable) {
                    seat.isSelected = !seat.isSelected
                    selectedSeats.clear()
                    selectedSeats.addAll(listSeats.filter { it.isSelected })
                    notifyDataSetChanged()
                }
            }


        }
    }

    fun getSelectedSeats(): List<Seats> {
        return selectedSeats
    }


}
