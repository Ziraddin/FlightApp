package com.example.flightapp.ui.adapters.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flightapp.databinding.ListSearchFlightsBinding

class FlightTicketAdapter(private val flight: MutableList<Flight>,val nav : ()->Unit) :
    RecyclerView.Adapter<FlightTicketAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            ListSearchFlightsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return flight.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = flight[position]
        return holder.bind(current)
    }

    inner class ViewHolder(private val binding: ListSearchFlightsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(current: Flight) {
            binding.txtTo.text  = current.txt2
            binding.txtFrom.text = current.txt1
            binding.txtDirectionFrom.text = current.from
            binding.txtDirectionTo.text = current.to
            binding.txtFromHour.text = current.hourFrom
            binding.txtToHour.text = current.hourTo
            binding.txtFromDate.text = current.dateFrom
            binding.txtToDate.text = current.daterTo
            binding.imgCompany.setImageResource(current.companyImg)
            binding.txtPrice.text=current.price.toString()
            binding.txtDuration.text = current.duration
            binding.txtCompanyName.text = current.companyName

            itemView.setOnClickListener {
                nav()
            }
        }
    }

}