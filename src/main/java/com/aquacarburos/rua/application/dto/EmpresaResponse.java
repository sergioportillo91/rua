package com.aquacarburos.rua.application.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EmpresaResponse {

    private Long id;

    private String nit;

    private String nombre;

    private String usuario;

    private String rol;

    private boolean pago;

    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaModificacion;

    private String clave;
}
