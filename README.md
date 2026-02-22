# RUA Backend

Este proyecto es el backend de RUA, desarrollado en Java usando Spring Boot. Gestiona la creación y consulta de empresas, formularios y autenticación.

## Requisitos
- Java 17+
- Maven
- Base de datos compatible (por defecto H2, configurable en `application.yml`)

## Instalación
1. Clona el repositorio.
2. Instala dependencias:
   ```powershell
   ./mvnw clean install
   ```
3. Configura la base de datos en `src/main/resources/application.yml`.

## Ejecución
Para iniciar el proyecto:
```powershell
./mvnw spring-boot:run
```

## Endpoints principales
- `/api/auth/login` - Autenticación de usuarios
- `/api/empresa` - Gestión de empresas
- `/api/form-rua` - Gestión de formularios RUA

Consulta la documentación en `docs/API-ENDPOINTS.md` para más detalles.

## Despliegue
Puedes desplegar el proyecto en Render, Heroku, o cualquier servicio compatible con Java.

## Recomendaciones para Render
- Configura variables de entorno para la base de datos y credenciales.
- Deshabilita la creación automática de tablas en producción.
- Usa un perfil de producción (`application-prod.yml`).

## Contacto
Para soporte, contacta a los desarrolladores del proyecto.

