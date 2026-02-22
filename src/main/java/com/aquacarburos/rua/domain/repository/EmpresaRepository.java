package com.aquacarburos.rua.domain.repository;

import com.aquacarburos.rua.domain.model.Empresa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EmpresaRepository {

    Empresa save(Empresa empresa);

    Optional<Empresa> findById(Long id);

    Optional<Empresa> findByNit(String nit);

    Optional<Empresa> findByUsuario(String usuario);

    List<Empresa> findAll();

    Page<Empresa> findAll(Pageable pageable);

}
