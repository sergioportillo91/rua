# Etapa 1: Construcción (Maven)
# Usamos una imagen de Maven con JDK 17 para compilar
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

# Copiamos el pom.xml y descargamos las dependencias (capa de caché)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiamos el código fuente y compilamos el JAR
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Imagen de ejecución (Ligera)
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copiamos el JAR generado desde la etapa de construcción
# Tu artifactId es 'rua' y la version '0.0.1-SNAPSHOT'
COPY --from=build /app/target/rua-0.0.1-SNAPSHOT.jar app.jar

# Exponemos el puerto definido en tu YAML
EXPOSE 8099

# Ejecutamos la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]