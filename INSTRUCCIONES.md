# Despliegue del Microservicio PurchaseOrder

Este documento detalla los pasos para desplegar el microservicio utilizando Docker y conectar la base de datos MySQL requerida.

## Prerrequisitos
- Tener Docker instalado y ejecutándose.
- Tener acceso a internet para descargar las imágenes.

## Pasos de Despliegue

### 1. Crear una Red Docker
Para que el microservicio pueda comunicarse con la base de datos, ambos contenedores deben estar en la misma red.


docker network create colina-network

### 2. Desplegar la Base de Datos MySQL

docker run -d --name mysqldb --network colina-network -e MYSQL_DATABASE=purchase_db -e MYSQL_ROOT_PASSWORD=password -p 3307:3306 mysql:8.0

### 3. Desplegar el Microservicio PurchaseOrder

docker run -d --name purchase-service --network colina-network -p 8080:8080 -e MYSQL_USER=root -e MYSQL_PASSWORD=password mrcolina/mrcolina_leccion2

### 4. Verificar el Despliegue

docker ps
http://localhost:8080/api/v1/purchase-orders

### 5. Pruebas con Postman

- Importa la colección Postman proporcionada en el repositorio.
- Realiza solicitudes a los endpoints para verificar el funcionamiento del microservicio.