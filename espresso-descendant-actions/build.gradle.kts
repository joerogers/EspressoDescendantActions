apply plugin: 'com.android.library'

android {
    namespace 'com.forkingcode.espresso.contrib'
    compileSdkVersion 33
    buildToolsVersion("33.0.2")

    defaultConfig {
        minSdkVersion 16
        targetSdkVersion 33
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
}

ext {
    libraryName = 'espresso-descendant-actions'
    libraryDescription = 'Custom espresso actions for working with descendant views'
    libraryVersion = rootProject.libraryVersion
}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    // TODO may need junit as well when moving to 3.4+
    api "androidx.test.espresso:espresso-core:3.1.0"
}