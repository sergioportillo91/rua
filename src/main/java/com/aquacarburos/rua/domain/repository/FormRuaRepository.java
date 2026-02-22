package com.aquacarburos.rua.domain.repository;

import com.aquacarburos.rua.domain.model.FormRua;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface FormRuaRepository {

    FormRua save(FormRua formRua);

    Optional<FormRua> findById(Long id);

    List<FormRua> findAll();

    Page<FormRua> findAll(Pageable pageable);

    Optional<FormRua> findByNit(String nit);
}
