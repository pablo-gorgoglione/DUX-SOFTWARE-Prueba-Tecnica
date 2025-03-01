# Usamos una imagen base con Java
FROM eclipse-temurin:17-jdk AS builder

# Configuramos el directorio de trabajo
WORKDIR /app

# Copiamos el código fuente y construimos el JAR
COPY . .
RUN ./mvnw package -DskipTests

# Creamos una imagen ligera para ejecutar el JAR
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

# Puerto en el que corre la app
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
# docker run -d -p 8080:8080 --name mi-app mi-app-spring