package com.example.loveapi.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.loveapi.PreferenceHelper
import com.example.loveapi.R
import com.example.loveapi.databinding.FragmentLoveCalculatorBinding
import com.example.loveapi.databinding.FragmentOnBoardBinding
import com.google.android.material.tabs.TabLayoutMediator

class OnBoardFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (PreferenceHelper.isOnboardingComplete(requireContext())) {
             findNavController().navigate(R.id.action_onBoardFragment_to_loveCalculatorFragment)
         } else {
        setupViewPager()
         }
    }

    private fun setupViewPager() {
        val adapter = OnBoardPagerAdapter(requireActivity())
        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
        }.attach()
    }
}