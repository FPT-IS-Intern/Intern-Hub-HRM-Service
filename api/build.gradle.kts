plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("application")
    id("java")
    id("com.google.cloud.tools.jib")
}

springBoot {
    mainClass.set("com.fis.internhub.HumanResourceServiceApplication")
}

application {
    mainClass.set("com.fis.internhub.HumanResourceServiceApplication")
}

dependencies {
    // Project dependencies
    implementation(project(":infra"))
    implementation(project(":core"))
    implementation(project(":common"))

    // Custom libraries bundle
    implementation(libs.bundles.custom.libraries)

    // Spring Boot all bundle (Web + Validation)
    implementation(libs.bundles.spring.boot.all)

    // Additional Spring Boot starters
    implementation(libs.spring.boot.starter)
    implementation(libs.spring.boot.starter.log4j2)
    implementation(libs.spring.boot.starter.data.jpa)
    implementation(libs.spring.boot.starter.security)
    implementation(libs.spring.boot.starter.actuator)

    // Documentation
    implementation(libs.openapi.doc)

    // MapStruct
    implementation(libs.mapstruct)
    annotationProcessor(libs.mapstruct.processor)
    annotationProcessor(libs.lombok.mapstruct.binding)

    // Lombok
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)

    // Configuration processor
    annotationProcessor(libs.spring.boot.configuration.processor)

    // Test
    testImplementation(libs.spring.boot.starter.webmvc.test)
    testImplementation(libs.spring.security.test)
}

jib {
    to.image = "huynhducphu2502/spring-boot-base-service"
}

tasks.bootJar {
    enabled = true
    archiveFileName = "hrm-service.jar"
}