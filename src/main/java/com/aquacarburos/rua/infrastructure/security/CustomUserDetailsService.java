package com.aquacarburos.rua.infrastructure.security;

import com.aquacarburos.rua.domain.model.Empresa;
import com.aquacarburos.rua.domain.repository.EmpresaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final EmpresaRepository empresaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Empresa empresa = empresaRepository.findByUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        return new User(
                empresa.getUsuario(),
                empresa.getClave(),
                Collections.singletonList(new SimpleGrantedAuthority(empresa.getRol().name()))
        );
    }
}
