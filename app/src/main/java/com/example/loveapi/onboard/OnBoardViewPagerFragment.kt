package com.example.loveapi.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.loveapi.PreferenceHelper
import com.example.loveapi.R
import com.example.loveapi.databinding.FragmentOnBoardViewPagerBinding

class OnBoardViewPagerFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardViewPagerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardViewPagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() {
        val position = arguments?.getInt(ARG_ONBOARD_POSITION) ?: 0
        with(binding) {
            when (position) {
                0 -> {
                    tvTitle.text = "Have a good time"
                    tvDescription.text = "You should take the time to help those who need you"
                    imageView.setImageResource(R.drawable.bg_onboard0)
                }
                1 -> {
                    tvTitle.text = "Cherishing love"
                    tvDescription.text = "It's now no longer possible for you to cherish love"
                    imageView.setImageResource(R.drawable.bg_onboard2)
                }
                2 -> {
                    tvTitle.text = "Have a breakup?"
                    tvDescription.text = "We have made the correction for you don't worry" +
                            "Maybe someone is waiting for you!"

                    imageView.setImageResource(R.drawable.bg_onboard3)
                    btnStart.visibility = View.VISIBLE
                    btnStart.setOnClickListener {
                        PreferenceHelper.setOnboardingComplete(requireContext(), true)
                        findNavController().navigate(R.id.action_onBoardFragment_to_loveCalculatorFragment)
                    }
                }
            }
        }
    }
    companion object {
        const val ARG_ONBOARD_POSITION = "onBoardPosition"
    }
}