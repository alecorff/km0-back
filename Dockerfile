# ===== STAGE 1 : BUILD =====
FROM eclipse-temurin:21-jdk-alpine AS build

WORKDIR /app

COPY mvnw .
COPY pom.xml .
COPY .mvn .mvn

# 🔑 rendre mvnw exécutable
RUN chmod +x mvnw

# Cache des dépendances
RUN ./mvnw -B dependency:go-offline

# Code source
COPY src src

# Build
RUN ./mvnw -B clean package -DskipTests

# ===== STAGE 2 : RUNTIME =====
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
