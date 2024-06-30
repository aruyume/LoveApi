package com.example.loveapi.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnBoardViewPagerAdapter (fragment: Fragment
): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int) = OnBoardViewPagerFragment().apply{
        arguments = Bundle().apply{
            putInt(OnBoardViewPagerFragment.ARG_ONBOARD_POSITION, position)
        }
    }
}