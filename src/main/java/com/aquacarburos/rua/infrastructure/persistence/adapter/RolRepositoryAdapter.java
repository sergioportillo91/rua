package com.aquacarburos.rua.infrastructure.persistence.adapter;

import com.aquacarburos.rua.domain.model.Rol;
import com.aquacarburos.rua.domain.model.RolNombre;
import com.aquacarburos.rua.domain.repository.RolRepository;
import com.aquacarburos.rua.infrastructure.persistence.entity.RolEntity;
import com.aquacarburos.rua.infrastructure.persistence.jpa.JpaRolRepository;
import com.aquacarburos.rua.infrastructure.persistence.mapper.RolMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RolRepositoryAdapter implements RolRepository {

    private final JpaRolRepository jpaRepository;

    private final RolMapper mapper;

    @Override
    public Rol save(Rol rol) {
        RolEntity entity = mapper.toEntity(rol);
        RolEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Rol> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<Rol> findByNombre(RolNombre nombre) {
        return jpaRepository.findByNombre(nombre).map(mapper::toDomain);
    }

    @Override
    public List<Rol> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }
}
