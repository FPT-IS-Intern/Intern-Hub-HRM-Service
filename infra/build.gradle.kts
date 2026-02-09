plugins {
    id("java-library")
}

dependencies {
    // Project dependencies
    api(project(":core"))
    api(project(":common"))

    // Custom libraries bundle
    implementation(libs.bundles.custom.libraries)

    // Spring Boot database bundle (JPA + PostgreSQL)
    implementation(libs.bundles.spring.boot.database)

    // Spring Boot validation
    implementation(libs.spring.boot.starter.validation)

    // Additional Spring Boot starters
    implementation(libs.spring.boot.starter)
    implementation(libs.spring.boot.starter.log4j2)
    implementation(libs.spring.boot.starter.kafka)
    implementation(libs.spring.boot.starter.liquibase)

    // MapStruct
    implementation(libs.mapstruct)
    annotationProcessor(libs.mapstruct.processor)

    // Lombok
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
    annotationProcessor(libs.lombok.mapstruct.binding)

    // Test
    testImplementation(libs.spring.boot.starter.test)
}

// Disable bootJar for library module
tasks.named("bootJar") {
    enabled = false
}

tasks.named("jar") {
    enabled = true
}