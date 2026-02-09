plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("application")
    id("java")

    id("com.google.cloud.tools.jib") version "3.5.2"
}

springBoot {
    mainClass.set("com.fis.internhub.HumanResourceServiceApplication")
}

application {
    mainClass.set("com.fis.internhub.HumanResourceServiceApplication")
}

dependencies {

    implementation(project(":infra"))
    implementation(project(":core"))
    implementation(project(":common"))

    implementation(libs.spring.boot.starter.webmvc)
    implementation(libs.spring.boot.starter.data.jpa)
    implementation(libs.spring.boot.starter.security)
    implementation(libs.spring.boot.starter.validation)
    implementation(libs.spring.boot.starter.actuator)

    implementation(libs.openapi.doc)

    implementation(libs.mapstruct)
    annotationProcessor(libs.mapstruct.processor)
    annotationProcessor(libs.lombok.mapstruct.binding)

    annotationProcessor(libs.spring.boot.configuration.processor)

    testImplementation(libs.spring.boot.starter.webmvc.test)
    testImplementation(libs.spring.security.test)
}

jib {
    to.image = "huynhducphu2502/spring-boot-base-service"
}