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

        init {
            binding.imgSeat.setOnClickListener {
                val seat = listSeats[adapterPosition]
                if (!seat.notAvailable) {
                    seat.isSelected = !seat.isSelected
                    if(seat.isSelected){
                        selectedSeats.add(seat)
                        notifyDataSetChanged()
                    }else{
                        selectedSeats.remove(seat)
                        notifyDataSetChanged()
                    }


                }

            }

        }

        fun bind(seat: Seats) {
            binding.imgSeat.setImageResource(
                when {
                    seat.isSelected -> R.drawable.seat_selected
                    seat.notAvailable -> R.drawable.seat_notavailable
                    else -> R.drawable.seat_available
                }
            )
        }
    }


    fun getSelectedSeats(): List<Seats> {
        return selectedSeats.filter { it.isSelected }
    }

}





