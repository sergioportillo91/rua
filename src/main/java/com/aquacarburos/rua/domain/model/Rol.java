package com.aquacarburos.rua.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rol {

    private Long id;

    private RolNombre nombre;
}
