-- =====================================================
-- Script de datos iniciales para RUA
-- Ejecutar directamente en PostgreSQL
-- =====================================================

-- Esquema: schema_rua
-- Nota: Las contraseñas están hasheadas con BCrypt

-- =====================================================
-- 1. INSERTAR ROLES
-- =====================================================
INSERT INTO schema_rua.rol (nombre) VALUES ('ROL_ADMIN');
INSERT INTO schema_rua.rol (nombre) VALUES ('ROL_CLIENTE');

-- =====================================================
-- 2. INSERTAR EMPRESAS
-- =====================================================
-- Contraseñas en texto plano para referencia:
--   admin123    -> $2a$10$N9qo8uLOickgx2ZMRZoMy.Mrq3cZFG13rCb4X8gx8oPQHQR9xZxKe
--   cliente123  -> $2a$10$EqKcp1WFKpoqaP8sNOpIUOYCxLqO3IM5ke3p00VkIR7X3sLkP2uiK
--   cliente456  -> $2a$10$h8C5mDZx8R5txPIGHN6BJuC9bQCZ3QFBE.n8B5A3x2Y6xRJwKEVXe

-- Empresa con ROL_ADMIN (pago = true)
INSERT INTO schema_rua.empresa (
    nit, 
    nombre, 
    usuario, 
    rol, 
    clave, 
    pago, 
    fecha_creacion, 
    fecha_modificacion
) VALUES (
    '900123456',
    'Administradora RUA S.A.S',
    'admin',
    'ROL_ADMIN',
    '$2a$10$N9qo8uLOickgx2ZMRZoMy.Mrq3cZFG13rCb4X8gx8oPQHQR9xZxKe',  -- admin123
    true,
    NOW(),
    NOW()
);

-- Empresa con ROL_CLIENTE y pago = true
INSERT INTO schema_rua.empresa (
    nit, 
    nombre, 
    usuario, 
    rol, 
    clave, 
    pago, 
    fecha_creacion, 
    fecha_modificacion
) VALUES (
    '800111222',
    'Estación de Servicio El Sol',
    'estacion_sol',
    'ROL_CLIENTE',
    'cliente123',  -- clave en texto plano
    true,
    NOW(),
    NOW()
);

-- Empresa con ROL_CLIENTE y pago = false
INSERT INTO schema_rua.empresa (
    nit, 
    nombre, 
    usuario, 
    rol, 
    clave, 
    pago, 
    fecha_creacion, 
    fecha_modificacion
) VALUES (
    '800333444',
    'Gasolinera La Luna',
    'gasolinera_luna',
    'ROL_CLIENTE',
    'cliente456',  -- clave en texto plano
    false,
    NOW(),
    NOW()
);

-- =====================================================
-- CREDENCIALES DE PRUEBA
-- =====================================================
-- | Usuario          | Contraseña   | Rol         | Pago  |
-- |------------------|--------------|-------------|-------|
-- | admin            | admin123     | ROL_ADMIN   | true  |
-- | estacion_sol     | cliente123   | ROL_CLIENTE | true  |
-- | gasolinera_luna  | cliente456   | ROL_CLIENTE | false |
-- =====================================================
