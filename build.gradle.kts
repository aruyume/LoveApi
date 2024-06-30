// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    //SafeArgs
    id("androidx.navigation.safeargs.kotlin") version "2.7.7" apply false
    //Ksp
    id("com.google.devtools.ksp") version "2.0.0-1.0.22" apply false
    //Dagger Hilt
    id("com.google.dagger.hilt.android") version "2.51.1" apply false
}