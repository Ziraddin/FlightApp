package com.example.flightapp.ui.activities.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.flightapp.databinding.FragmentViewPagerBinding
import com.example.flightapp.ui.activities.onboarding.screens.OnBoardingFirstFragment
import com.example.flightapp.ui.activities.onboarding.screens.OnBoardingSecondFragment
import com.example.flightapp.ui.activities.onboarding.screens.OnBoardingThirdFragment


class ViewPagerFragment : Fragment() {
    private lateinit var binding: FragmentViewPagerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewPagerBinding.inflate(inflater)
        val fragmentList = arrayListOf<Fragment>(
            OnBoardingFirstFragment(),
            OnBoardingSecondFragment(),
            OnBoardingThirdFragment()
        )
        val adapter =
            ViewPagerAdapter(fragmentList, requireActivity().supportFragmentManager, lifecycle)

        binding.viewPager.adapter = adapter
        return binding.root

    }

}