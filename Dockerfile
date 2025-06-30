# Usar imagen base oficial de Java 17
FROM eclipse-temurin:17-jdk-jammy

# Crear directorio para la app
WORKDIR /app

# Copiar el jar generado al contenedor
COPY target/treebars-backend-0.0.1-SNAPSHOT.jar app.jar

# Exponer puerto 8080 (el que usa Spring Boot)
EXPOSE 8080

# Comando para ejecutar la app
ENTRYPOINT ["java","-jar","app.jar"]
