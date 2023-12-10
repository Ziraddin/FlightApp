package com.example.flightapp.ui.adapters.recyclerview

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.flightapp.R
import com.example.flightapp.databinding.ListSearchFlightsBinding
import com.example.flightapp.model.Flight
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class FlightTicketAdapter(private var flight: MutableList<Flight>, val nav: (Bundle) -> Unit) :
    RecyclerView.Adapter<FlightTicketAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            ListSearchFlightsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return flight.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = flight[position]
        return holder.bind(current)
    }

    fun updateData(newFlight: MutableList<Flight>) {
        flight = newFlight
        notifyDataSetChanged()
    }


    fun addData(newFlight: MutableList<Flight>) {
        flight.addAll(newFlight)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ListSearchFlightsBinding) :
        RecyclerView.ViewHolder(binding.root) {


        private fun formatDate(inputDate: String?): String {
            if (inputDate != null) {
                val inputFormat = SimpleDateFormat("yyyy-MM-dd-HH:mm:ss", Locale.getDefault())
                val outputFormat = SimpleDateFormat("MMM dd", Locale.getDefault())

                try {
                    val date = inputFormat.parse(inputDate)
                    return outputFormat.format(date!!)
                } catch (e: ParseException) {
                    e.printStackTrace()
                }
            }
            return ""
        }

        private fun extractHoursAndMinutes(dateTime: String?): Pair<String, String> {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd-HH:mm:ss", Locale.getDefault())
            val date = inputFormat.parse(dateTime ?: "")
            val outputFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

            if (date != null) {
                val formattedTime = outputFormat.format(date)
                val hoursMinutes = formattedTime.split(":")
                if (hoursMinutes.size == 2) {
                    return Pair(hoursMinutes[0], hoursMinutes[1])
                }
            }
            return Pair("", "")
        }


        @RequiresApi(Build.VERSION_CODES.O)
        fun calculateDuration(departureTime: String, arrivalTime: String): String {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss")
            val departureDateTime = LocalDateTime.parse(departureTime, formatter)
            val arrivalDateTime = LocalDateTime.parse(arrivalTime, formatter)
            val duration = Duration.between(departureDateTime, arrivalDateTime)
            return "${duration.toHours()} h"
        }

        //TODO(Zeynalli)
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(current: Flight) {
            val hoursAndMinutesFrom = extractHoursAndMinutes(current.departureTime)
            val hoursAndMinutesTo = extractHoursAndMinutes(current.arrivalTime)
            binding.txtTo.text = current.departure?.substring(0, 3)
            binding.txtFrom.text = current.arrival?.substring(0, 3)
            binding.txtDirectionFrom.text = current.departure
            binding.txtDirectionTo.text = current.arrival
            binding.txtFromHour.text = hoursAndMinutesFrom.first + ":" + hoursAndMinutesFrom.second
            binding.txtToHour.text = hoursAndMinutesTo.first + ":" + hoursAndMinutesTo.second
            binding.txtFromDate.text = formatDate(current.departureTime)
            binding.txtToDate.text = formatDate(current.arrivalTime)
            binding.imgCompany.setImageResource(R.drawable.turkishairlines)
            binding.txtPrice.text = current.price.toString()
            binding.txtDuration.text =
                calculateDuration(current.departureTime!!, current.arrivalTime!!)
            binding.txtCompanyName.text = current.company

            itemView.setOnClickListener {
                val bundle = Bundle()
                bundle.putSerializable("flight",current)
                nav(bundle)
            }
        }
    }

}