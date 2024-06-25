package com.example.loveapi.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.loveapi.retrofit.App
import com.example.loveapi.R
import com.example.loveapi.databinding.FragmentLoveCalculatorBinding
import com.example.loveapi.mvp.LoveContract
import com.example.loveapi.mvp.LoveModel
import com.example.loveapi.mvp.LovePresenter
import com.example.loveapi.retrofit.LoveResult
import androidx.lifecycle.ViewModelProvider
import com.example.loveapi.App
import com.example.loveapi.model.LoveResult
import com.example.loveapi.R
import com.example.loveapi.databinding.FragmentLoveCalculatorBinding
import com.example.loveapi.mvvm.LoveViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoveCalculatorFragment : Fragment(), LoveContract.View {

    private lateinit var binding: FragmentLoveCalculatorBinding
    private lateinit var presenter: LoveContract.Presenter
  
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
        binding = FragmentLoveCalculatorBinding.inflate(inflater, container, false)
        presenter = LovePresenter(this, LoveModel())
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
                        findNavController().navigate(R.id.action_loveCalculatorFragment_to_loveResultFragment, bundle)

            presenter.calculateLovePercentage(firstName, secondName)
        }
    }

    override fun showProgress() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.progressBar.visibility = View.GONE
    }

    override fun showResult(loveResult: LoveResult) {
        val bundle = Bundle().apply {
            putString("firstName", loveResult.firstName)
            putString("secondName", loveResult.secondName)
            putInt("percentage", loveResult.percentage.toIntOrNull() ?: 0)
        }
        val loveResultFragment = LoveResultFragment().apply {
            arguments = bundle
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
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, loveResultFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun showError(error: String) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
    }
}