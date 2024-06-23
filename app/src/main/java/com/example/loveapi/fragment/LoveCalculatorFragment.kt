package com.example.loveapi.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.loveapi.App
import com.example.loveapi.model.LoveResult
import com.example.loveapi.R
import com.example.loveapi.databinding.FragmentLoveCalculatorBinding
import com.example.loveapi.mvvm.LoveViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoveCalculatorFragment : Fragment() {

    private val binding: FragmentLoveCalculatorBinding by lazy {
        FragmentLoveCalculatorBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[LoveViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCalculate.setOnClickListener {
            val firstName = binding.etFirstName.text.toString()
            val secondName = binding.etSecondName.text.toString()

            if (firstName.isBlank() || secondName.isBlank()) {
                Toast.makeText(requireContext(), "Enter both names", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            viewModel.getLoveResult(
                firstName = firstName,
                secondName = secondName
            ).observe(viewLifecycleOwner) { loveResults ->
                if (loveResults != null) {
                    val percentage = loveResults.percentage.toIntOrNull() ?: 0
                    val bundle = Bundle().apply {
                        putString("firstName", firstName)
                        putString("secondName", secondName)
                        putInt("percentage", percentage)
                    }
                    val resultFragment = LoveResultFragment().apply {
                        arguments = bundle
                    }
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, resultFragment)
                        .addToBackStack(null).commit()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Error: ",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}
