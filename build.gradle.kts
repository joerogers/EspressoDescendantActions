// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "9.4.0" apply false
    id("com.android.library") version "9.4.0" apply false
    // Kapt needed for data binding
    id("com.android.legacy-kapt") version "9.4.0" apply false
    // ADD: The Kotlin Android plugin (use your desired Kotlin 2.x version)
    id("org.jetbrains.kotlin.android") version "2.4.20" apply false

    id("com.vanniktech.maven.publish") version "0.37.0" apply false
}