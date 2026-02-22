package com.aquacarburos.rua.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmpresaRequest {

    @NotBlank(message = "El NIT no puede estar vacío")
    @Size(min = 5, max = 50, message = "El NIT debe tener entre 5 y 50 caracteres")
    private String nit;

    @NotBlank(message = "El nombre de la empresa es obligatorio")
    private String nombre;

    @NotBlank(message = "El usuario es obligatorio")
    private String usuario;

    @Size(min = 6, message = "La clave debe tener al menos 6 caracteres")
    private String clave;

    private boolean pago;

    private String rol;
}