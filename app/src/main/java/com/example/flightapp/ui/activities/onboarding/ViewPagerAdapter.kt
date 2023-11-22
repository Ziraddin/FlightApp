package com.example.flightapp.ui.activities.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flightapp.R
import com.example.flightapp.databinding.FragmentOnBoardingFirstBinding
import com.example.flightapp.ui.activities.onboarding.screens.onboardingVpDto

class ViewPagerAdapter(private val data: List<onboardingVpDto>) :
    RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {

    inner class ViewHolder(view: FragmentOnBoardingFirstBinding) : RecyclerView.ViewHolder(view.root) {
        val image = view.viewpagerObIv
        val text = view.viewpagerObTv
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FragmentOnBoardingFirstBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.image.setImageResource(data[position].image)
        holder.text.text = data[position].text
    }
}