# Backend para el Sistema de Gestión BOSS 🏗️

Este es el backend para la plataforma de gestión empresarial **BOSS**, enfocada en el sector construcción para la empresa **Polyline SAC**. El frontend está desarrollado en **Angular**.

Este proyecto es desarrollado como parte del curso **Soluciones Web y Aplicaciones Distribuidas** con **NRC 5206**, correspondiente al período **UPN 2026-1**.

## ARQUITECTURA: n-capas

El sistema implementa una arquitectura de capas bien definida para separar las responsabilidades de control, lógica de negocio y persistencia de datos:
*   **Controllers:** Exposición de endpoints REST.
*   **Services:** Implementación de la lógica de negocio.
*   **Repositories:** Interacción con la base de datos mediante Spring Data JPA.
*   **Models/Entities:** Representación de los objetos de datos del sistema.

## Configuración

*   **Project:** Maven
*   **Language:** Java
*   **Spring Boot:** 3.x.x
*   **Packaging:** jar
*   **Java:** 17 o superior
*   **Archivo de configuración:** `application.properties`

## Recomendaciones

Cuando clone el proyecto, revise los siguientes archivos de configuración:
1.  **application.properties:** Para validar puertos en los que ejecuta la aplicación, cadena de conexión a la base de datos, usuario y contraseña.
2.  **pom.xml:** Dependencias y versiones de los frameworks utilizados (Spring Boot, MySQL Connector, Lombok, etc.).

## Documentación

La API está diseñada siguiendo los principios de servicios RESTful. Se recomienda que la evolución del proyecto siga el **Modelo de Madurez de Richardson**, revisando la documentación de Martin Fowler para alcanzar niveles superiores de madurez en servicios web.

## Dependencias y Base de Datos

El proyecto está configurado por defecto para trabajar con **MySQL**. Revise el motor de base de datos y la cadena de conexión en el archivo `application.properties` y actualícelo según corresponda.

### Configuración de MySQL

La dependencia incluida en el `pom.xml` es:

```xml
<!-- Conexión a base de datos -->
<!-- Source: https://mvnrepository.com/artifact/com.mysql/mysql-connector-j -->
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <version>8.3.0</version>
    <scope>compile</scope>
</dependency>
