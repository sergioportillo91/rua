package com.aquacarburos.rua.application.service;

import com.aquacarburos.rua.application.dto.LoginRequest;
import com.aquacarburos.rua.application.dto.LoginResponse;
import com.aquacarburos.rua.domain.model.Empresa;
import com.aquacarburos.rua.domain.model.RolNombre;
import com.aquacarburos.rua.domain.model.exception.PagoPendienteException;
import com.aquacarburos.rua.domain.repository.EmpresaRepository;
import com.aquacarburos.rua.infrastructure.security.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private EmpresaRepository empresaRepository;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthService authService;

    private Empresa empresaConPago;
    private Empresa empresaSinPago;
    private LoginRequest loginRequest;

    @BeforeEach
    void setUp() {
        empresaConPago = new Empresa();
        empresaConPago.setId(1L);
        empresaConPago.setNit("900123456");
        empresaConPago.setUsuario("empresa1");
        empresaConPago.setClave("password123"); // clave en texto plano
        empresaConPago.setRol(RolNombre.ROL_CLIENTE);
        empresaConPago.setNombre("Empresa Test");
        empresaConPago.setPago(true);

        empresaSinPago = new Empresa();
        empresaSinPago.setId(2L);
        empresaSinPago.setNit("800654321");
        empresaSinPago.setUsuario("empresa2");
        empresaSinPago.setClave("password123"); // clave en texto plano
        empresaSinPago.setRol(RolNombre.ROL_CLIENTE);
        empresaSinPago.setNombre("Empresa Sin Pago");
        empresaSinPago.setPago(false);

        loginRequest = new LoginRequest();
        loginRequest.setUsuario("empresa1");
        loginRequest.setClave("password123");
    }

    @Test
    @DisplayName("Login exitoso con empresa que tiene pago al día")
    void login_conPagoAlDia_retornaToken() {
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(null);
        when(empresaRepository.findByUsuario("empresa1"))
                .thenReturn(Optional.of(empresaConPago));
        when(jwtService.generateToken(any()))
                .thenReturn("jwt.token.example");

        LoginResponse response = authService.login(loginRequest);

        assertNotNull(response);
        assertEquals("jwt.token.example", response.getToken());
        assertEquals("empresa1", response.getUsuario());
        assertEquals("ROL_CLIENTE", response.getRol());
        assertEquals(1L, response.getEmpresaId());
        assertEquals("900123456", response.getNit());
        assertEquals("Empresa Test", response.getNombreEmpresa());
        verify(authenticationManager).authenticate(any());
    }

    @Test
    @DisplayName("Login fallido por pago pendiente lanza PagoPendienteException")
    void login_conPagoPendiente_lanzaExcepcion() {
        loginRequest.setUsuario("empresa2");
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(null);
        when(empresaRepository.findByUsuario("empresa2"))
                .thenReturn(Optional.of(empresaSinPago));

        assertThrows(PagoPendienteException.class, () -> authService.login(loginRequest));
    }

    @Test
    @DisplayName("Login fallido por usuario no encontrado lanza UsernameNotFoundException")
    void login_usuarioNoExiste_lanzaExcepcion() {
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(null);
        when(empresaRepository.findByUsuario("empresa1"))
                .thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> authService.login(loginRequest));
    }
}
