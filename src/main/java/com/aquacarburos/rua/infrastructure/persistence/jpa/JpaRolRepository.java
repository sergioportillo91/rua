package com.aquacarburos.rua.infrastructure.persistence.jpa;

import com.aquacarburos.rua.domain.model.RolNombre;
import com.aquacarburos.rua.infrastructure.persistence.entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaRolRepository extends JpaRepository<RolEntity, Long> {

    Optional<RolEntity> findByNombre(RolNombre nombre);
}
