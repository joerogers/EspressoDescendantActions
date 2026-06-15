import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.espresso.contrib"
    compileSdk = 37

    defaultConfig {
        applicationId = "com.forkingcode.espresso.contrib.sample"
        minSdk = 23
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        dataBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.19.0")
    implementation("androidx.appcompat:appcompat:1.7.1")
    implementation("androidx.recyclerview:recyclerview:1.4.0")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("com.google.android.material:material:1.14.0")

    // Kotlin
    val kotlinBom = platform("org.jetbrains.kotlin:kotlin-bom:2.3.10")
    implementation(kotlinBom)
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    androidTestImplementation(kotlinBom)

    // tests
    androidTestImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test:runner:1.7.0")
    androidTestImplementation("androidx.test:rules:1.7.0")
    androidTestImplementation("androidx.test.ext:junit:1.3.0")

    val espressoVersion = "3.7.0"
    androidTestImplementation("androidx.test.espresso:espresso-core:${espressoVersion}")
    androidTestImplementation("androidx.test.espresso:espresso-contrib:${espressoVersion}")

    //androidTestImplementation(project(":espresso-descendant-actions"))
    androidTestImplementation("com.forkingcode.espresso.contrib:espresso-descendant-actions:1.7.0")
}
