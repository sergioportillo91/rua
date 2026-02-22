# API RUA - Documentación de Endpoints

**Base URL:** `http://localhost:8080`

**Swagger UI:** `http://localhost:8080/swagger-ui.html`

---

## Autenticación

Todos los endpoints (excepto los marcados como públicos) requieren un token JWT en el header:

```
Authorization: Bearer <token>
```

---

## 1. Autenticación (`/api/v1/auth`)

### POST `/api/v1/auth/login` 🔓 Público

Iniciar sesión y obtener token JWT.

**Request Body:**
```json
{
  "usuario": "admin",
  "clave": "admin123"
}
```

**Response 200:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "tipo": "Bearer"
}
```

**Response 401:** Credenciales inválidas  
**Response 402:** Pago pendiente (empresa no ha pagado)

---

## 2. Empresas (`/api/v1/empresas`)

### POST `/api/v1/empresas` 🔓 Público

Crear una nueva empresa/gasolinera.

**Request Body:**
```json
{
  "nit": "900123456",
  "nombre": "Mi Gasolinera S.A.S",
  "usuario": "mi_gasolinera",
  "clave": "password123"
}
```

**Response 201:**
```json
{
  "id": 1,
  "nit": "900123456",
  "nombre": "Mi Gasolinera S.A.S",
  "usuario": "mi_gasolinera",
  "rol": "ROL_CLIENTE",
  "pago": false,
  "fechaCreacion": "2026-02-18T10:30:00",
  "fechaModificacion": "2026-02-18T10:30:00"
}
```

---

### GET `/api/v1/empresas` 🔒 Requiere Auth

Listar empresas con paginación.

**Query Parameters:**
| Parámetro | Tipo | Default | Descripción |
|-----------|------|---------|-------------|
| `page` | int | 0 | Número de página (0-indexed) |
| `size` | int | 10 | Elementos por página |
| `sortBy` | string | id | Campo por el cual ordenar |
| `sortDir` | string | asc | Dirección (asc/desc) |

**Ejemplo:** `GET /api/v1/empresas?page=0&size=5&sortBy=nombre&sortDir=desc`

**Response 200:**
```json
{
  "content": [
    {
      "id": 1,
      "nit": "900123456",
      "nombre": "Administradora RUA S.A.S",
      "usuario": "admin",
      "rol": "ROL_ADMIN",
      "pago": true,
      "fechaCreacion": "2026-02-18T10:30:00",
      "fechaModificacion": "2026-02-18T10:30:00"
    }
  ],
  "page": 0,
  "size": 10,
  "totalElements": 3,
  "totalPages": 1,
  "first": true,
  "last": true
}
```

---

### GET `/api/v1/empresas/{id}` 🔒 Requiere Auth

Obtener empresa por ID.

**Response 200:**
```json
{
  "id": 1,
  "nit": "900123456",
  "nombre": "Administradora RUA S.A.S",
  "usuario": "admin",
  "rol": "ROL_ADMIN",
  "pago": true,
  "fechaCreacion": "2026-02-18T10:30:00",
  "fechaModificacion": "2026-02-18T10:30:00"
}
```

**Response 404:** Empresa no encontrada

---

### GET `/api/v1/empresas/nit/{nit}` 🔒 Requiere Auth

Obtener empresa por NIT.

**Ejemplo:** `GET /api/v1/empresas/nit/900123456`

**Response 200:** (igual que GET por ID)  
**Response 404:** Empresa no encontrada

---

### PUT `/api/v1/empresas/{id}` 🔒 Requiere Auth

Actualizar datos de una empresa.

**Request Body:**
```json
{
  "nit": "900123456",
  "nombre": "Nombre Actualizado S.A.S",
  "usuario": "usuario_nuevo",
  "clave": "nueva_clave",
  "pago": true
}
```

**Response 200:** Empresa actualizada  
**Response 404:** Empresa no encontrada

---

## 3. Formularios RUA (`/api/v1/form-rua`)

### POST `/api/v1/form-rua` 🔒 Requiere Auth

Guardar un formulario RUA.

**Request Body:**
```json
{
  "nit": "900123456",
  "nombreEmpresa": "Gasolinera El Sol",
  "direccion": "Calle 123 #45-67",
  "telefono": "3001234567",
  "diagramaActividadPdf": "<base64_pdf>",
  "certificadoGeneradorRespel2025": "<base64_pdf>",
  "contratoGestorResiduosPeligrosos": "<base64_pdf>"
  // ... campos adicionales
}
```

**Response 201:** Formulario creado exitosamente  
**Response 400:** Error de validación

---

### GET `/api/v1/form-rua` 🔒 Requiere Auth

Listar formularios RUA con paginación.

**Query Parameters:**
| Parámetro | Tipo | Default | Descripción |
|-----------|------|---------|-------------|
| `page` | int | 0 | Número de página (0-indexed) |
| `size` | int | 10 | Elementos por página |
| `sortBy` | string | id | Campo por el cual ordenar |
| `sortDir` | string | asc | Dirección (asc/desc) |

**Response 200:**
```json
{
  "content": [...],
  "page": 0,
  "size": 10,
  "totalElements": 25,
  "totalPages": 3,
  "first": true,
  "last": false
}
```

---

### GET `/api/v1/form-rua/{id}` 🔒 Requiere Auth

Obtener formulario RUA por ID.

**Response 200:** Formulario encontrado  
**Response 404:** Formulario no encontrado

---

## Códigos de Error Comunes

| Código | Descripción |
|--------|-------------|
| 200 | OK - Operación exitosa |
| 201 | Created - Recurso creado |
| 400 | Bad Request - Error de validación |
| 401 | Unauthorized - Token inválido o expirado |
| 402 | Payment Required - Empresa no ha pagado |
| 404 | Not Found - Recurso no encontrado |
| 500 | Internal Server Error |

---

## Credenciales de Prueba

| Usuario | Contraseña | Rol | Pago |
|---------|------------|-----|------|
| admin | admin123 | ROL_ADMIN | ✅ |
| estacion_sol | cliente123 | ROL_CLIENTE | ✅ |
| gasolinera_luna | cliente456 | ROL_CLIENTE | ❌ |

---

## Ejemplo de Uso con cURL

**Login:**
```bash
curl -X POST http://localhost:8080/api/v1/auth/login \
  -H "Content-Type: application/json" \
  -d '{"usuario":"admin","clave":"admin123"}'
```

**Listar empresas (con token):**
```bash
curl -X GET "http://localhost:8080/api/v1/empresas?page=0&size=10" \
  -H "Authorization: Bearer <tu_token>"
```

**Crear empresa:**
```bash
curl -X POST http://localhost:8080/api/v1/empresas \
  -H "Content-Type: application/json" \
  -d '{"nit":"123456789","nombre":"Nueva Gasolinera","usuario":"nueva","clave":"password123"}'
```
