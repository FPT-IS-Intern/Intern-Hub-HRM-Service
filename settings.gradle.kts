rootProject.name = "intern-hub-hrm-service"

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("libs.versions.toml"))
        }
    }
    repositories {
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
        mavenLocal()
    }
}

include(
    "api",
    "core",
    "infra"
)
