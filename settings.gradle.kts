rootProject.name = "Intern-Hub-Human-Resource-Service"

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
        mavenLocal()
    }
}

include(
    "common",
    "api",
    "core",
    "infra"
)
