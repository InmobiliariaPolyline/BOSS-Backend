# Etapa 1: Compilación limpia con Maven y Java 21
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN chmod +x mvnw && ./mvnw clean package -DskipTests

# Etapa 2: Imagen final ultra ligera para ejecutar la aplicación
# CAMBIO: Se corrigió '21-mdw-jammy' por '21-jre-jammy'
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Copiamos el JAR generado en la etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Configuración para el Port Binding de Render
EXPOSE 8080

# Usamos ENTRYPOINT en formato exec para evitar problemas con la propagación de señales y variables
ENTRYPOINT ["java", "-jar", "app.jar"]