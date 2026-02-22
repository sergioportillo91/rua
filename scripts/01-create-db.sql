-- =====================================================
-- PASO 1: Ejecutar conectado a 'postgres' (no a rua_db)
-- =====================================================
DROP DATABASE IF EXISTS rua_db;
CREATE DATABASE rua_db;

-- =====================================================
-- PASO 2: Conectarse a rua_db y ejecutar lo siguiente
-- =====================================================
CREATE SCHEMA IF NOT EXISTS schema_rua;
