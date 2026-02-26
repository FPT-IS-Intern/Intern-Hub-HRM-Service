plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("application")
}

repositories {
    maven { url = uri("https://jitpack.io") }
    mavenCentral()
}

springBoot {
    mainClass.set("com.fis.hrmservice.api.Main")
}

application {
    mainClass.set("com.fis.hrmservice.api.Main")
}

dependencies {
    // Project dependencies
    implementation(project(":infra"))
    implementation(project(":core"))

    // Custom libraries
    implementation(libs.bundles.custom.libraries)

    // Spring Boot
    implementation(libs.bundles.spring.boot.all)
    implementation(libs.spring.boot.starter.log4j2)
    implementation(libs.spring.boot.starter.data.jpa)
    implementation(libs.spring.boot.starter.security)

    // Documentation
    implementation(libs.openapi.doc)

    // MapStruct
    implementation(libs.mapstruct)
    implementation(libs.spring.boot.starter.webflux)
    implementation(libs.spring.cloud.loadbalancer)
    implementation(libs.spring.cloud.feign)
    testImplementation(libs.reactor.test)
    annotationProcessor(libs.mapstruct.processor)
    annotationProcessor(libs.lombok.mapstruct.binding)

    // Lombok
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)


    // Configuration processor
    annotationProcessor(libs.spring.boot.configuration.processor)
}

tasks.bootJar {
    enabled = true
    archiveFileName = "hrm-service.jar"
}
