package com.example.loveapi

import android.content.Context
import android.content.SharedPreferences

object PreferenceHelper {
    private const val PREFERENCES_NAME = "love_api_prefs"
    private const val KEY_ONBOARDING_COMPLETE = "onboarding_complete"

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    fun isOnboardingComplete(context: Context): Boolean {
        return getPreferences(context).getBoolean(KEY_ONBOARDING_COMPLETE, false)
    }

    fun setOnboardingComplete(context: Context, complete: Boolean) {
        getPreferences(context).edit().putBoolean(KEY_ONBOARDING_COMPLETE, complete).apply()
    }
}
