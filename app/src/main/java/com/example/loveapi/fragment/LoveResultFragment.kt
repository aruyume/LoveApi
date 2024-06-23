package com.example.loveapi.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.loveapi.R
import com.example.loveapi.databinding.FragmentLoveResultBinding

class LoveResultFragment : Fragment() {

    private val binding by lazy {
        FragmentLoveResultBinding.inflate(layoutInflater)
    }
    private val binding: FragmentLoveResultBinding by lazy {
        FragmentLoveResultBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
            val loveCalculatorFragment = LoveCalculatorFragment()

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, loveCalculatorFragment)
                .addToBackStack(null)
                .commit()
        }
    }
}
