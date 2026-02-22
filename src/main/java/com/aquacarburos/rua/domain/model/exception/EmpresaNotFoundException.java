package com.aquacarburos.rua.domain.model.exception;

public class EmpresaNotFoundException extends RuntimeException {

    public EmpresaNotFoundException(Long id) {
        super("Empresa no encontrada con id: " + id);
    }

    public EmpresaNotFoundException(String nit) {
        super("Empresa no encontrada con NIT: " + nit);
    }
}
