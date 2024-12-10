#!/bin/bash

# Configuración del contenedor
MYSQL_CONTAINER_NAME="mysql"       # Nombre del contenedor
MYSQL_ROOT_PASSWORD="password"    # Contraseña del usuario root
MYSQL_DATABASE="test"         # Nombre de la base de datos inicial
MYSQL_USER="admin"                      # Usuario adicional
MYSQL_PASSWORD="password"              # Contraseña del usuario adicional
MYSQL_PORT=3306                             # Puerto local para MySQL


if [ "$(docker ps -a -q -f name=$MYSQL_CONTAINER_NAME)" ]; then
    echo "Un contenedor con el nombre $MYSQL_CONTAINER_NAME ya existe. Deteniéndolo y eliminándolo..."
    docker stop $MYSQL_CONTAINER_NAME > /dev/null 2>&1
    docker rm $MYSQL_CONTAINER_NAME > /dev/null 2>&1
fi

# Crear y ejecutar el contenedor de MySQL
echo "Iniciando un nuevo contenedor de MySQL..."
docker run -d \
  --name $MYSQL_CONTAINER_NAME \
  -e MYSQL_ROOT_PASSWORD=$MYSQL_ROOT_PASSWORD \
  -e MYSQL_DATABASE=$MYSQL_DATABASE \
  -e MYSQL_USER=$MYSQL_USER \
  -e MYSQL_PASSWORD=$MYSQL_PASSWORD \
  -p $MYSQL_PORT:3306 \
  mysql:8.0

if [ $? -eq 0 ]; then
    echo "Contenedor MySQL iniciado con éxito."
    echo "Nombre del contenedor: $MYSQL_CONTAINER_NAME"
    echo "Puerto: $MYSQL_PORT"
    echo "Base de datos: $MYSQL_DATABASE"
    echo "Usuario: $MYSQL_USER"
else
    echo "Hubo un problema al iniciar el contenedor de MySQL."
    exit 1
fi

# Mostrar contenedores activos
docker ps
