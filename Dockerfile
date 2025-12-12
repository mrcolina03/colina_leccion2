FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copia el JAR generado por Maven

COPY ./target/colina-leccion2-0.0.1-SNAPSHOT.jar ./colina-leccion2-0.0.1-SNAPSHOT.jar

EXPOSE 8080

# Ejecuta la aplicación activando el perfil 'prod'
ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","colina-leccion2-0.0.1-SNAPSHOT.jar"]