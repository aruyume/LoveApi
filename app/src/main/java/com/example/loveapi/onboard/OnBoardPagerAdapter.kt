package com.example.loveapi.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnBoardPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = OnBoardViewPagerFragment()
        fragment.arguments = Bundle().apply {
            putInt(OnBoardViewPagerFragment.ARG_ONBOARD_POSITION, position)
        }
        return fragment
    }
}