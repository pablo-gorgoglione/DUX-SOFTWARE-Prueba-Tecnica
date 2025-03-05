
# Dux - Prueba técnica

Este proyecto contiene las funcionalidades básicas de una API RESTFUL incluyendo:

- **Autenticación**: Registro e inicio de sesión de usuarios con seguridad basada en **JWT**.
- **CRUD de Equipos**: Administración de equipos, incluyendo la creación, lectura, actualización y eliminación de equipos.
- **Base de Datos en Memoria**: Utiliza **H2** como base de datos en memoria para pruebas.

## Ejecución de la aplicación

Para ejecutar la aplicación, puedes usar el siguiente script:

```bash
./run-backend.sh
```
Este script construye y ejecuta la aplicación, que estará disponible en el puerto **8080**.

### Alternativa: Ejecución Local (sin Docker)

Si deseas ejecutar la aplicación localmente sin usar Docker, sigue estos pasos:

1. **Clona el repositorio**:
   ```bash
   git clone https://tu-repositorio-url.git
   cd base-api
   ```

2. **Construye el proyecto con Maven**:
   Asegúrate de tener Maven instalado en tu máquina. Si no lo tienes, puedes descargarlo desde [aquí](https://maven.apache.org/download.cgi).

   ```bash
   mvn clean install
   ```

3. **Ejecuta la aplicación**:
   Ejecuta el siguiente comando para arrancar la aplicación:

   ```bash
   mvn spring-boot:run
   ```

   La aplicación estará disponible en `http://localhost:8080`.



## Acceso a la documentación de la API

Una vez que la aplicación esté en funcionamiento, puedes acceder a la documentación interactiva de la API a través de Swagger UI en la siguiente URL:

[http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/)

## Colección de Postman

Adjunto la colección de Postman que puedes utilizar para probar la API de la aplicación. La colección contiene los endpoints principales, incluyendo autenticación y CRUD de equipos.

[Descargar colección de Postman](https://github.com/pablo-gorgoglione/test-api/blob/aa368adeb6de98a2d8306b2ed681c85ab14f4240/dux-challenge.postman_collection.json)
