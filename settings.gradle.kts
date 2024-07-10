pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        // Uncomment to test local preview
        // mavenLocal()
    }
}

rootProject.name = "EspressoDescendantActions"
include(":espresso-descendant-actions")
include(":sample")
