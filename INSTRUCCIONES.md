# Descripción del Proyecto

Este proyecto consiste en un microservicio desarrollado en **Spring Boot (Java 17)** para la gestión de Órdenes de Compra (Purchase Orders). El sistema permite crear, listar, filtrar y validar órdenes, persistiendo la información en una base de datos **MySQL**.

La arquitectura está diseñada para ser desplegada mediante contenedores **Docker**, utilizando un perfil de producción que gestiona la conexión y la creación automática del esquema de base de datos.

## Despliegue del Microservicio PurchaseOrder

Este documento detalla los pasos para desplegar el microservicio utilizando Docker y conectar la base de datos MySQL requerida.

## Prerrequisitos
- Tener Docker instalado y ejecutándose.
- Tener acceso a internet para descargar las imágenes.

- Descargar la imagen del microservicio desde Docker Hub:

docker pull mrcolina/mrcolina_leccion2

## Pasos de Despliegue

### 1. Crear una Red Docker
Para que el microservicio pueda comunicarse con la base de datos, ambos contenedores deben estar en la misma red.


docker network create colina-network

### 2. Desplegar la Base de Datos MySQL

docker run -d --name mysqldb --network colina-network -e MYSQL_DATABASE=purchase_db -e MYSQL_ROOT_PASSWORD=password -p 3307:3306 mysql:8.0

### Nota
En caso de no querer crear un nuevo contenedor de MySQL, asegúrate de que el contenedor existente esté en la red `colina-network` y que la base de datos `purchase_db` esté creada.

### 3. Desplegar el Microservicio PurchaseOrder

docker run -d --name purchase-service --network colina-network -p 8080:8080 -e MYSQL_USER=root -e MYSQL_PASSWORD=password mrcolina/mrcolina_leccion2

### Nota
Si es que no se creó un nuevo contenedor de MySQL, asegúrate de ajustar las variables de entorno `MYSQL_USER` y `MYSQL_PASSWORD` según las credenciales del contenedor existente.

### 4. Verificar el Despliegue

docker ps

### Acceder al Microservicio
http://localhost:8080/api/v1/purchase-orders

### 5. Pruebas con Postman

- Importa la colección Postman proporcionada en el repositorio.
- Realiza solicitudes a los endpoints para verificar el funcionamiento del microservicio.