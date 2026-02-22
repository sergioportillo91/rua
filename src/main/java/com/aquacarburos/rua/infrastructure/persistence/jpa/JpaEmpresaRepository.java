package com.aquacarburos.rua.infrastructure.persistence.jpa;

import com.aquacarburos.rua.infrastructure.persistence.entity.EmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaEmpresaRepository extends JpaRepository<EmpresaEntity, Long> {

    Optional<EmpresaEntity> findByNit(String nit);

    Optional<EmpresaEntity> findByUsuario(String usuario);
}