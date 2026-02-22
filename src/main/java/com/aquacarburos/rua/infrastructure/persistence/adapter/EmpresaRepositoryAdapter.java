package com.aquacarburos.rua.infrastructure.persistence.adapter;

import com.aquacarburos.rua.domain.model.Empresa;
import com.aquacarburos.rua.domain.repository.EmpresaRepository;
import com.aquacarburos.rua.infrastructure.persistence.entity.EmpresaEntity;
import com.aquacarburos.rua.infrastructure.persistence.jpa.JpaEmpresaRepository;
import com.aquacarburos.rua.infrastructure.persistence.mapper.EmpresaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EmpresaRepositoryAdapter implements EmpresaRepository {

    private final JpaEmpresaRepository jpaRepository;

    private final EmpresaMapper mapper;

    @Override
    public Empresa save(Empresa empresa) {
        EmpresaEntity entity = mapper.toEntity(empresa);
        EmpresaEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Empresa> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<Empresa> findByNit(String nit) {
        return jpaRepository.findByNit(nit).map(mapper::toDomain);
    }

    @Override
    public Optional<Empresa> findByUsuario(String usuario) {
        return jpaRepository.findByUsuario(usuario).map(mapper::toDomain);
    }

    @Override
    public List<Empresa> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Page<Empresa> findAll(Pageable pageable) {
        return jpaRepository.findAll(pageable).map(mapper::toDomain);
    }

}