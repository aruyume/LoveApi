package com.example.loveapi.sharedpreference

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferencesHelper @Inject constructor(private val sharedPreferences: SharedPreferences) {

    companion object {
        private const val PREF_ONBOARDING_SHOWN = "onboarding_shown"
    }

    fun isOnBoardShown(): Boolean {
        return sharedPreferences.getBoolean(PREF_ONBOARDING_SHOWN, false)
    }

    fun setOnBoardShown() {
        sharedPreferences.edit().putBoolean(PREF_ONBOARDING_SHOWN, true).apply()
    }
}
