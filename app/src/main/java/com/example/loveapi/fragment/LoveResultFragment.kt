package com.example.loveapi.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.loveapi.R
import com.example.loveapi.databinding.FragmentLoveResultBinding

class LoveResultFragment : Fragment() {

    private lateinit var binding: FragmentLoveResultBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoveResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val firstName = arguments?.getString("firstName")
        val secondName = arguments?.getString("secondName")
        val percentage = arguments?.getInt("percentage")

        binding.tvFirstNameResult.text = firstName
        binding.tvSecondNameResult.text = secondName
        binding.tvPercentage.text = "$percentage%"

        binding.btnTryAgain.setOnClickListener {
            findNavController().navigate(R.id.action_loveResultFragment_to_loveCalculatorFragment)
        }
    }
}