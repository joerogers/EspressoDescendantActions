apply plugin: 'com.android.application'

android {
    namespace 'com.example.espresso.contrib'
    compileSdkVersion 33
    buildToolsVersion("33.0.2")

    defaultConfig {
        applicationId "com.sample.espresso.contrib"
        minSdkVersion 21
        targetSdkVersion 33
        versionCode 1
        versionName "1.0"

        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }

    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation fileTree(include: ['*.jar'], dir: 'libs')
    implementation 'androidx.appcompat:appcompat:1.6.1'
    implementation 'androidx.recyclerview:recyclerview:1.3.0'
    implementation 'androidx.cardview:cardview:1.0.0'

    // tests
    androidTestImplementation 'junit:junit:4.13.2'
    androidTestImplementation "androidx.test:runner:1.5.2"
    androidTestImplementation "androidx.test:rules:1.5.0"
    androidTestImplementation 'androidx.test.ext:junit:1.1.5'

    def espressoVersion = '3.5.1'
    androidTestImplementation "androidx.test.espresso:espresso-core:${espressoVersion}"
    androidTestImplementation "androidx.test.espresso:espresso-contrib:${espressoVersion}"
    //androidTestImplementation project(":espresso-descendant-actions")
    androidTestImplementation 'com.forkingcode.espresso.contrib:espresso-descendant-actions:1.4.0'
}
