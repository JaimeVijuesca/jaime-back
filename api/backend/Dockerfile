# Etapa de build
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app

# Copiar pom.xml desde backend/
COPY api/backend/pom.xml .
RUN mvn dependency:go-offline

# Copiar código fuente desde backend/src
COPY api/backend/src ./src
# Build del proyecto
RUN mvn clean package -DskipTests

# Etapa de ejecución
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copiar el JAR exacto generado desde la etapa builder
COPY --from=builder /app/target/backend-0.0.1-SNAPSHOT.jar app.jar




# Comando para arrancar la app
CMD ["java", "-jar", "app.jar"]
