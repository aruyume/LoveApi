package com.example.loveapi.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.loveapi.PreferenceHelper
import com.example.loveapi.R
import com.example.loveapi.databinding.FragmentOnBoardStartBinding

class OnBoardStartFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardStartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (PreferenceHelper.isOnboardingComplete(requireContext())) {
            findNavController().navigate(R.id.action_onBoardStartFragment_to_onBoardFragment)
        } else {
            PreferenceHelper.setOnboardingComplete(requireContext(), true)

            binding.btnStart.setOnClickListener {
                findNavController().navigate(R.id.action_onBoardStartFragment_to_onBoardFragment)
            }
        }
    }
}