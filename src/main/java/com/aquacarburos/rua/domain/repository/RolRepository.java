package com.aquacarburos.rua.domain.repository;

import com.aquacarburos.rua.domain.model.Rol;
import com.aquacarburos.rua.domain.model.RolNombre;

import java.util.List;
import java.util.Optional;

public interface RolRepository {

    Rol save(Rol rol);

    Optional<Rol> findById(Long id);

    Optional<Rol> findByNombre(RolNombre nombre);

    List<Rol> findAll();
}
