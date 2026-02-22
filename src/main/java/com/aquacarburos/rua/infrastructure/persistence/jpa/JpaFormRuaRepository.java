package com.aquacarburos.rua.infrastructure.persistence.jpa;

import com.aquacarburos.rua.infrastructure.persistence.entity.FormRuaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface JpaFormRuaRepository extends JpaRepository<FormRuaEntity, Long> {
    Optional<FormRuaEntity> findByNit(String nit);
    List<FormRuaEntity> findByNitAndNombreEmpresa(String nit, String nombreEmpresa);
}
