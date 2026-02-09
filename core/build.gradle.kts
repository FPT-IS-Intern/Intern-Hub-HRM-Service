plugins {
    id("java-library")
}

dependencies {
    // Project dependencies
    implementation(project(":common"))

    // Custom libraries bundle (common, security)
    implementation(libs.bundles.custom.libraries)

    // Spring Core
    implementation(libs.spring.boot.starter)
    implementation(libs.spring.boot.starter.log4j2)
    
    // Spring Data JPA (for @Modifying annotation)
    implementation(libs.spring.boot.starter.data.jpa)

    // Utils
    implementation(libs.hutool)

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