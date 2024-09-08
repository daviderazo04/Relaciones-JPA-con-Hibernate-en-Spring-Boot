# Relaciones JPA en Spring Boot

Este proyecto es un ejemplo de cómo manejar relaciones bidireccionales **One-to-Many** y **Many-to-One** utilizando JPA en una aplicación de Spring Boot. Incluye ejemplos de mapeo entre entidades como `Client`, `Invoice` y `Address`.

## Características

- Relación **One-to-Many** entre `Client` y `Invoice`.
- Relación **One-to-Many** entre `Client` y `Address`.
- Métodos para crear, actualizar y eliminar entidades de manera transaccional. (CRUD)

## Tecnologías

- Java 17
- Spring Boot 3.x
- JPA / Hibernate

## Uso

1. Clona este repositorio.
2. Importa el proyecto en tu IDE favorito.
3. Ejecuta la aplicación usando el comando:

   ```bash
   ./mvnw spring-boot:run
