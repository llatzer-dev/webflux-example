# 📦 WebFluxUsuarios

API REST reactiva con Spring WebFlux para la gestión de usuarios.

---

## 🚀 Tecnologías usadas

- Java 17
- Spring Boot 3.x
- Spring WebFlux
- Project Reactor
- Maven

---

## 📁 Estructura del proyecto

```
com.ejemplo.webfluxusuarios
├── controller
│   └── UserController.java
├── model
│   └── User.java
├── repository
│   └── UserRepository.java
└── WebfluxUsuariosApplication.java
```

---

## 📦 Endpoints disponibles

### ✅ Obtener todos los usuarios
- **GET** `/api/usuarios`
- **Respuesta:** `Flux<User>`

### 🔍 Obtener usuario por ID
- **GET** `/api/usuarios/{id}`
- **Respuesta:** `Mono<User>`
- **Errores:**
    - ID no encontrado → `Usuario no encontrado`
    - Usuario "Carlos" lanza error de ejemplo → `Carlos está bloqueado`

---

## 👨‍💻 Programación reactiva en acción

Esta API usa `Mono`, `Flux` y operadores reactivos como:

- `doOnNext()` → para loguear cada emisión
- `doOnSuccess()` y `doOnError()` → para manejo de éxito/error
- `handle()` → para controlar la emisión condicional
- `switchIfEmpty()` → para manejar el caso de valores vacíos
- `doOnSubscribe()` → para trazar suscripciones

---

## 🧪 Ejemplos de prueba

```bash
# Obtener todos los usuarios
curl http://localhost:8080/api/usuarios

# Obtener usuario por ID existente
curl http://localhost:8080/api/usuarios/1

# Usuario bloqueado (Carlos)
curl http://localhost:8080/api/usuarios/3

# Usuario inexistente
curl http://localhost:8080/api/usuarios/99
```

---

### Ejemplos de prueba CRUD

```bash
# Create
curl -X POST http://localhost:8080/api/usuarios -H "Content-Type: application/json" -d "{\"id\":\"4\",\"nombre\":\"Ana\",\"email\":\"ana@example.com\"}"

# Read
curl http://localhost:8080/api/usuarios

# Read
curl http://localhost:8080/api/usuarios/1

# Update
curl -X PUT http://localhost:8080/api/usuarios/1 -H "Content-Type: application/json" -d "{\"id\":\"1\",\"nombre\":\"Lázaro Actualizado\",\"email\":\"lazaro.new@example.com\"}"

# Delete
curl -X DELETE http://localhost:8080/api/usuarios/4
```

---

## 🏁 Ejecutar el proyecto

```bash
# Clonar el repositorio
cd webfluxusuarios

# Ejecutar con Maven
./mvnw spring-boot:run
```

O desde tu IDE ejecutando `WebfluxUsuariosApplication.java` directamente.

---

## 🔮 Futuras mejoras

- Añadir servicio `UserService` con inyección de dependencias
- Persistencia con MongoDB o R2DBC
- Validaciones con Bean Validation (`@Valid`)
- Tests con `WebTestClient`
- Control global de errores (`@ControllerAdvice`)

---

## 📬 Autor

**Lázaro Ortega Boix** — [LinkedIn](https://www.linkedin.com/in/lazaro-ortega-boix/)

---

> Proyecto creado para practicar programación reactiva con Spring WebFlux.