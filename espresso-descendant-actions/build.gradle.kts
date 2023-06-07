plugins {
    id("com.android.library")
    id("maven-publish")
    id("signing")
}

android {
    namespace = "com.forkingcode.espresso.contrib"
    compileSdk = 30

    defaultConfig {
        minSdk =  16
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
}

val libraryName = "espresso-descendant-actions"
val libraryDescription = "Custom espresso actions for working with descendant views"
val libraryVersion = "1.5.0"

dependencies {
    // TODO may need junit as well when moving to 3.4+
    api("androidx.test.espresso:espresso-core:3.1.0")
}
