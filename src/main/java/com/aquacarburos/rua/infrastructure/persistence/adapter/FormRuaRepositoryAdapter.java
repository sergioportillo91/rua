package com.aquacarburos.rua.infrastructure.persistence.adapter;

import com.aquacarburos.rua.domain.model.FormRua;
import com.aquacarburos.rua.domain.repository.FormRuaRepository;
import com.aquacarburos.rua.infrastructure.persistence.entity.FormRuaEntity;
import com.aquacarburos.rua.infrastructure.persistence.jpa.JpaFormRuaRepository;
import com.aquacarburos.rua.infrastructure.persistence.mapper.FormRuaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FormRuaRepositoryAdapter implements FormRuaRepository {

    private final JpaFormRuaRepository jpaRepository;

    private final FormRuaMapper mapper;

    @Override
    public FormRua save(FormRua formRua) {
        FormRuaEntity entity = mapper.toEntity(formRua);
        FormRuaEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<FormRua> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<FormRua> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Page<FormRua> findAll(Pageable pageable) {
        return jpaRepository.findAll(pageable).map(mapper::toDomain);
    }

    @Override
    public Optional<FormRua> findByNit(String nit) {
        return jpaRepository.findByNit(nit).map(mapper::toDomain);
    }
}
