-- Script de migración para dividir caudal_entrada_salida_ls en caudal_entrada_ls y caudal_salida_ls
-- Ejecutar en la base de datos correspondiente

ALTER TABLE schema_rua.form_rua
    ADD COLUMN caudal_entrada_ls VARCHAR(255),
    ADD COLUMN caudal_salida_ls VARCHAR(255);

-- Si se requiere migrar datos existentes, descomentar y ajustar la lógica:
-- UPDATE schema_rua.form_rua
--    SET caudal_entrada_ls = caudal_entrada_salida_ls,
--        caudal_salida_ls = caudal_entrada_salida_ls;

ALTER TABLE schema_rua.form_rua
    DROP COLUMN caudal_entrada_salida_ls;

