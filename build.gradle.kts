import com.diffplug.gradle.spotless.SpotlessExtension
import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension

plugins {
    alias(libs.plugins.spring.boot) apply false
    alias(libs.plugins.dependency.management) apply false
    alias(libs.plugins.spotless) apply false
    alias(libs.plugins.sonar) apply false
    alias(libs.plugins.jib) apply false
    java
}

allprojects {
    group = "fis.internhub"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
        mavenLocal()
    }
}

subprojects {

    apply(plugin = "java")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "com.diffplug.spotless")
    apply(plugin = "org.sonarqube")

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(25))
        }
    }

    configure<DependencyManagementExtension> {
        imports {
            mavenBom(
                "org.springframework.cloud:spring-cloud-dependencies:" +
                        rootProject.libs.versions.spring.cloud.get()
            )
        }
    }

    configurations.configureEach {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
        exclude(group = "ch.qos.logback", module = "logback-classic")
    }

    configure<SpotlessExtension> {
        java {
            googleJavaFormat(rootProject.libs.versions.googleJavaFormat.get())
            importOrder()
            removeUnusedImports()
            endWithNewline()
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    // Disable bootJar for library modules
    tasks.withType<org.springframework.boot.gradle.tasks.bundling.BootJar> {
        enabled = false
    }

    tasks.withType<Jar> {
        enabled = true
    }
}
