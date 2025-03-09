
### ESTRUCTURA DEL BACKEND

```bash
/backend
 ├── src/
 │   ├── application/         # Lógica de negocio (casos de uso, servicios)
 │   ├── domain/              # Entidades y lógica de dominio
 │   ├── infrastructure/      # Adaptadores y conexiones externas
 │   │   ├── controllers/     # Controladores de la API (REST, GraphQL)
 │   │   ├── repositories/    # Implementaciones de persistencia (DAO)
 │   │   ├── security/        # Autenticación y autorización
 │   │   ├── config/          # Configuraciones de la app
 │   │   ├── scheduler/       # Tareas programadas
 │   ├── shared/              # Utilidades y código reutilizable
 │   ├── Main.java            # Punto de entrada
 ├── test/                    # Tests unitarios y de integración
 ├── docker/                  # Configuración de contenedores Docker
 ├── docs/                    # Documentación del proyecto
 ├── .env                     # Variables de entorno
 ├── pom.xml / package.json    # Dependencias (según el stack)
 ├── README.md                 # Información del proyecto

```