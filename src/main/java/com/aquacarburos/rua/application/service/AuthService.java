package com.aquacarburos.rua.application.service;

import com.aquacarburos.rua.application.dto.LoginRequest;
import com.aquacarburos.rua.application.dto.LoginResponse;
import com.aquacarburos.rua.domain.model.Empresa;
import com.aquacarburos.rua.domain.model.exception.PagoPendienteException;
import com.aquacarburos.rua.domain.repository.EmpresaRepository;
import com.aquacarburos.rua.infrastructure.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final EmpresaRepository empresaRepository;
    private final JwtService jwtService;

    public LoginResponse login(LoginRequest request) {
        Empresa empresa = empresaRepository.findByUsuario(request.getUsuario())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        if (!empresa.isPago()) {
            throw new PagoPendienteException();
        }

        // Validar clave en texto plano
        if (!empresa.getClave().equals(request.getClave())) {
            throw new UsernameNotFoundException("Clave incorrecta");
        }

        UserDetails userDetails = new User(
                empresa.getUsuario(),
                empresa.getClave(),
                Collections.singletonList(new SimpleGrantedAuthority(empresa.getRol().name()))
        );

        String token = jwtService.generateToken(userDetails);

        return LoginResponse.builder()
                .token(token)
                .usuario(empresa.getUsuario())
                .rol(empresa.getRol().name())
                .empresaId(empresa.getId())
                .nit(empresa.getNit())
                .nombreEmpresa(empresa.getNombre())
                .build();
    }
}
