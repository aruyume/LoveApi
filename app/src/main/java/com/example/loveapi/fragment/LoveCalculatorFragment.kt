package com.example.loveapi.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.loveapi.retrofit.App
import com.example.loveapi.retrofit.LoveResult
import com.example.loveapi.R
import com.example.loveapi.databinding.FragmentLoveCalculatorBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoveCalculatorFragment : Fragment() {

    private lateinit var binding: FragmentLoveCalculatorBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoveCalculatorBinding.inflate(inflater, container, false)
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

            App().api?.getPercentage(
                firstName = firstName,
                key = "13db8c0c9fmsh0e8b65404615b3ap1035a5jsn85bfe5faab5c",
                host = "love-calculator.p.rapidapi.com",
                secondName = secondName
            )?.enqueue(object : Callback<LoveResult> {
                override fun onResponse(call: Call<LoveResult>, response: Response<LoveResult>) {
                    if (response.isSuccessful && response.body() != null) {

                        val loveResult = response.body()
                        val percentage = loveResult?.percentage?.toIntOrNull() ?: 0
                        val bundle = Bundle().apply {
                            putString("firstName", firstName)
                            putString("secondName", secondName)
                            putInt("percentage", percentage)
                        }
                        val loveResultFragment = LoveResultFragment().apply {
                            arguments = bundle
                        }
                        parentFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, loveResultFragment)
                            .addToBackStack(null)
                            .commit()

                    } else {
                        Toast.makeText(requireContext(), "Error: ${response.code()}", Toast.LENGTH_LONG).show()
                    }
                }
                override fun onFailure(call: Call<LoveResult>, throwable: Throwable) {
                    Toast.makeText(requireContext(), throwable.message, Toast.LENGTH_LONG).show()
                }
            })
        }
    }
}
