# ===== STAGE 1 : BUILD =====
FROM eclipse-temurin:21-jdk-alpine AS build

WORKDIR /app

# Copie uniquement ce qui est nécessaire pour le build
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

# Téléchargement des deps (cache Docker)
RUN ./mvnw -B dependency:go-offline

# Copie du code
COPY src src

# Build du JAR
RUN ./mvnw -B clean package -DskipTests

# ===== STAGE 2 : RUNTIME =====
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
