package com.aquacarburos.rua.domain.model.exception;

public class PagoPendienteException extends RuntimeException {

    public PagoPendienteException(String mensaje) {
        super(mensaje);
    }

    public PagoPendienteException() {
        super("No puede iniciar sesión: la empresa tiene pago pendiente.");
    }
}
