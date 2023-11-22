package com.example.flightapp.ui.activities.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.flightapp.R
import com.example.flightapp.databinding.FragmentViewPagerBinding
import com.example.flightapp.ui.activities.onboarding.screens.onboardingVpDto


class ViewPagerFragment : Fragment() {
    private lateinit var binding: FragmentViewPagerBinding
    lateinit var viewPager: ViewPager2
    lateinit var data : List<onboardingVpDto>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentViewPagerBinding.inflate(inflater)
         data = listOf<onboardingVpDto>(
            onboardingVpDto(R.drawable.onboardingfirst, resources.getString(R.string.firstOnBoarding)),
            onboardingVpDto(
                R.drawable.onboardingsecond,
                resources.getString(R.string.secondOnBoarding)
            ),
            onboardingVpDto(R.drawable.onboardingthird, resources.getString(R.string.thirdOnBoarding))
        )
        viewPager = binding.viewPager
        viewPager.isUserInputEnabled = false
        setAdapter(data)
        setButtonsListeners()
        return binding.root
    }

    private fun setAdapter(data: List<onboardingVpDto>) {
        val adapter = ViewPagerAdapter(data)
        val dotsIndicator = binding.dotsIndicator
        viewPager.adapter = adapter
        dotsIndicator.attachTo(viewPager)
    }

    private fun setButtonsListeners(){
        val btn_skip = binding.btnSkip
        val btn_next = binding.btnNext

        btn_skip.setOnClickListener {
            findNavController().navigate(R.id.toSignInFragment)
        }
        btn_next.apply {
            setOnClickListener {
                if (viewPager.currentItem < (data.size-1)) {
                    viewPager.currentItem++
                    if (viewPager.currentItem == 2){
                        text = "Continue"
                    }
                } else {
                    findNavController().navigate(R.id.toSignInFragment)
                }
            }
        }
    }
}