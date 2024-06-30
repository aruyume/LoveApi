package com.example.loveapi.sharedpreference

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

    @Module
    @InstallIn(SingletonComponent::class)
    object SharedPreferencesModule {

        @Provides
        @Singleton
        fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
            return context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        }

        @Provides
        @Singleton
        fun provideSharedPreferencesHelper(sharedPreferences: SharedPreferences): SharedPreferencesHelper {
            return SharedPreferencesHelper(sharedPreferences)
        }
    }