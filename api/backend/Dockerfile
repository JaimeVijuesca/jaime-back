# Etapa de build
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app

# Copiar pom.xml desde backend/
COPY backend/pom.xml .
RUN mvn dependency:go-offline

# Copiar código fuente desde backend/src
COPY backend/src ./src

# Build del proyecto
RUN mvn clean package -DskipTests

# Etapa de ejecución
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copiar JAR compilado
COPY --from=builder /app/target/*.jar app.jar

# Comando para arrancar la app
CMD ["java", "-jar", "app.jar"]
