package com.example.flightapp.ui.adapters.recyclerview



import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.flightapp.databinding.ListApprovedseatBinding


class SelectedSeatAdapter(private var listSeats: List<Seats>) :
    RecyclerView.Adapter<SelectedSeatAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            ListApprovedseatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = listSeats[position]
        return holder.bind(current)
    }
    override fun getItemCount(): Int {
        return listSeats.size
    }


    inner class ViewHolder(private val binding: ListApprovedseatBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(seat: Seats) {
           binding.txtSeatNumber.text = "1"
           binding.txtOrderTicker.text = "5A"

        }
    }

    fun updateSelectedSeats(newSelectedSeats: List<Seats>) {
        listSeats = newSelectedSeats.toMutableList()
        notifyDataSetChanged()
    }

}