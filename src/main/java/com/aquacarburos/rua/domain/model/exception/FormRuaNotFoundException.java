package com.aquacarburos.rua.domain.model.exception;

public class FormRuaNotFoundException extends RuntimeException {

    public FormRuaNotFoundException(Long id) {
        super("Formulario RUA no encontrado con id: " + id);
    }
}
