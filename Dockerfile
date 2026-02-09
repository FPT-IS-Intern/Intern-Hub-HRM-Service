# ===== BUILD STAGE =====
FROM eclipse-temurin:25-jdk AS builder

WORKDIR /app

COPY . .

RUN chmod +x gradlew

# chỉ build api
RUN ./gradlew :api:bootJar -x test --no-daemon


# ===== RUNTIME STAGE =====
FROM eclipse-temurin:25-jre

WORKDIR /app

COPY --from=builder /app/api/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
