package com.aquacarburos.rua.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

    private String token;
    private String usuario;
    private String rol;
    private Long empresaId;
    private String nit;
    private String nombreEmpresa;
}
