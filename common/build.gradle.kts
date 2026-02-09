plugins {
    id("java-library")
}

dependencies {
    // Spring Core
    implementation(libs.spring.boot.starter)
    implementation(libs.spring.boot.starter.log4j2)

    // Utils
    implementation(libs.hutool)
    implementation(libs.jakarta.validation)

    // Lombok
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)

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
