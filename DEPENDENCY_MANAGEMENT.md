# Dependency Management Guide

## Overview

This project uses Gradle Version Catalog to centralize dependency management across all modules. All library versions
and dependencies are defined in `gradle/libs.versions.toml`.

## Structure

### Version Catalog Location

`gradle/libs.versions.toml`

### Version Catalog Sections

#### 1. **Versions**

All version numbers are centralized in the `[versions]` section:

- `spring-boot`: Spring Boot framework version
- `dependency-management`: Spring dependency management plugin version
- `lombok`: Lombok annotation processor version
- `lombok-mapstruct-binding`: Lombok-MapStruct binding version
- `postgres`: PostgreSQL JDBC driver version
- `mapstruct`: MapStruct mapping framework version
- `common-library`: Custom common library version
- `security-starter`: Custom security starter version
- `redis-starter`: Custom Redis starter version

#### 2. **Plugins**

Gradle plugins are defined in the `[plugins]` section:

- `spring-boot`: Spring Boot Gradle plugin
- `dependency-management`: Spring dependency management plugin

#### 3. **Libraries**

Individual libraries are defined in the `[libraries]` section and can be referenced using:

```kotlin
implementation(libs.library.name)
```

#### 4. **Bundles**

Commonly used library groups are organized into bundles in the `[bundles]` section:

- **spring-boot-all**: Web + Validation
  ```kotlin
  implementation(libs.bundles.spring.boot.all)
  ```

- **spring-boot-database**: JPA + PostgreSQL
  ```kotlin
  implementation(libs.bundles.spring.boot.database)
  ```

- **custom-libraries**: Common Library + Security Starter + Redis Starter
  ```kotlin
  implementation(libs.bundles.custom.libraries)
  ```

## Module Dependencies

### Core Module (`core/build.gradle.kts`)

- Custom libraries bundle (common, security, redis)
- Lombok for annotations
- MapStruct for object mapping

### Infra Module (`infra/build.gradle.kts`)

- Core module dependency
- Custom libraries bundle
- Spring Boot database bundle (JPA + PostgreSQL)
- Spring Boot validation
- Lombok and MapStruct

### API Module (`api/build.gradle.kts`)

- Custom libraries bundle
- Spring Boot all bundle (Web + Validation)
- Lombok

### App Module (`app/build.gradle.kts`)

- All other project modules (API, Infra, Core)
- Custom libraries bundle
- Spring Boot starters (Web, Security, Data JPA)
- Lombok

## How to Update Dependencies

### Update a Version

1. Open `gradle/libs.versions.toml`
2. Locate the version in the `[versions]` section
3. Update the version number
4. Run `./gradlew clean build --refresh-dependencies`

### Add a New Dependency

1. Add the version to `[versions]` section (if needed)
2. Add the library definition to `[libraries]` section
3. Optionally add to a bundle in `[bundles]` section
4. Reference it in module's `build.gradle.kts` file

### Example: Adding a New Library

```toml
# In gradle/libs.versions.toml
[versions]
new-library = "1.0.0"

[libraries]
new-library = { module = "com.example:new-library", version.ref = "new-library" }
```

Then in any module's `build.gradle.kts`:

```kotlin
dependencies {
    implementation(libs.new.library)
}
```

## Benefits

1. **Centralized Version Management**: All versions in one place
2. **Type-Safe References**: IDE autocomplete support
3. **Reusability**: Bundles reduce duplication
4. **Consistency**: Same versions across all modules
5. **Easy Updates**: Update version once, affects all modules

## Common Tasks

### Refresh Dependencies

```bash
./gradlew clean build --refresh-dependencies
```

### View Dependency Tree

```bash
./gradlew :module-name:dependencies
```

### Check for Dependency Updates

```bash
./gradlew dependencyUpdates
```

## Notes

- All common dependencies (Lombok, MapStruct, custom libraries) are now explicitly defined in each module that uses them
- The root `build.gradle.kts` only contains project-level configuration
- Each module declares only the dependencies it actually needs
- Bundles help group related dependencies and reduce duplication

