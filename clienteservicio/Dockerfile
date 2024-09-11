# Usa una imagen base de Java
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo en el contenedor
WORKDIR /app

# Copia el jar del proyecto al contenedor
COPY target/clienteservicio-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto 8080 para acceso externo
EXPOSE 8080

# Define el comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
