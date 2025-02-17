pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        // Si tu utilises QiSDK, ajoute le repo SoftBank
        maven {
            url = uri("https://qisdk.softbankrobotics.com/sdk-beta/maven")
        }
    }
}

rootProject.name = "MyPepperApplication"
include(":app")
