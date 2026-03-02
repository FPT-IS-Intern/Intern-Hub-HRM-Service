plugins {
    id("java-library")
}

dependencies {
    // OpenTelemetry
    implementation(platform(libs.opentelemetry.bom))
    implementation(libs.opentelemetry.sdk)
    implementation(libs.opentelemetry.sdk.trace)

    // Project dependencies
    // Custom libraries
    implementation(libs.bundles.custom.libraries)

    // Spring Core
    implementation(libs.spring.boot.starter)
    implementation(libs.spring.boot.starter.log4j2)
    implementation(libs.spring.boot.starter.security)

    // Spring Data JPA (for @Modifying annotation)
    implementation(libs.spring.boot.starter.data.jpa)

    // MapStruct
    implementation(libs.mapstruct)
    annotationProcessor(libs.mapstruct.processor)

    // Lombok
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
    annotationProcessor(libs.lombok.mapstruct.binding)
}
