import com.vanniktech.maven.publish.AndroidSingleVariantLibrary
import com.vanniktech.maven.publish.JavadocJar
import com.vanniktech.maven.publish.SourcesJar

plugins {
    id("com.android.library")
    id("com.vanniktech.maven.publish")
    id("signing")
}

android {
    namespace = "com.forkingcode.espresso.contrib"
    compileSdk = 36

    defaultConfig {
        minSdk = 21
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    api("androidx.test.espresso:espresso-core:3.7.0")
}

// Publishing info

val libraryGroupId = "com.forkingcode.espresso.contrib"
val libraryName = "espresso-descendant-actions"
val libraryDescription = "Custom espresso actions for working with descendant views"
val libraryVersion = "1.7.0"

mavenPublishing {
    publishToMavenCentral()
    signAllPublications()

    configure(
        AndroidSingleVariantLibrary(
            javadocJar = JavadocJar.Javadoc(),
            sourcesJar = SourcesJar.Sources(),
            variant = "release"
        )
    )

    coordinates(groupId = libraryGroupId, artifactId = libraryName, version = libraryVersion)

    pom {
        packaging = "aar"

        // Add your description here
        name.set(libraryName)
        description.set(libraryDescription)
        url.set("https://github.com/joerogers/EspressoDescendantActions")

        // Set your license
        licenses {
            license {
                name.set("The Apache Software License, Version 2.0")
                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }

        developers {
            developer {
                id.set("joerogers")
                name.set("Joe Rogers")
                url.set("https://github.com/joerogers")
            }
        }
        scm {
            connection.set("scm:git:git://github.com/joerogers/EspressoDescendantActions.git")
            developerConnection.set("scm:git:git://github.com/joerogers/EspressoDescendantActions.git")
            url.set("https://github.com/joerogers/EspressoDescendantActions")
        }
    }
}