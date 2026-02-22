# Etapa 1: Construcción (Maven)
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

# Copiamos el pom.xml y descargamos dependencias
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiamos el código fuente y compilamos
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Imagen de ejecución (Más moderna y estable)
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copiamos el JAR generado
COPY --from=build /app/target/rua-0.0.1-SNAPSHOT.jar app.jar

# Exponemos el puerto
EXPOSE 8099

# Ejecutamos la aplicación con optimización de memoria para Render
ENTRYPOINT ["java", "-Xmx300m", "-jar", "app.jar"]