package com.aquacarburos.rua.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.aquacarburos.rua.domain.model.RolNombre;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Empresa {

    private Long id;

    private String nit;

    private String nombre;

    private String usuario;

    private RolNombre rol;

    private String clave;

    private boolean pago;

    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaModificacion;
}