package com.aquacarburos.rua.application.service;

import com.aquacarburos.rua.domain.model.FormRua;
import com.aquacarburos.rua.domain.model.exception.FormRuaNotFoundException;
import com.aquacarburos.rua.domain.repository.FormRuaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FormRuaService {

    private final FormRuaRepository formRuaRepository;

    public FormRua crear(FormRua formRua) {
        String nitNormalizado = normalizarNit(formRua.getNit());
        String nombreEmpresa = formRua.getNombreEmpresa();
        if (nitNormalizado.isBlank()) {
            throw new IllegalArgumentException("El NIT es obligatorio y solo debe contener números.");
        }
        formRua.setNit(nitNormalizado);

        List<FormRua> registros = formRuaRepository.findByNitAndNombre(nitNormalizado, nombreEmpresa);
        if (!registros.isEmpty()) {
            throw new IllegalArgumentException("La empresa ya ha hecho su registro con ese NIT y nombre.");
        }

        return formRuaRepository.save(formRua);
    }

    public List<FormRua> listarTodos() {
        return formRuaRepository.findAll();
    }

    public Page<FormRua> listarTodos(Pageable pageable) {
        return formRuaRepository.findAll(pageable);
    }

    public FormRua buscarPorId(Long id) {
        return formRuaRepository.findById(id).orElseThrow(() -> new FormRuaNotFoundException(id));
    }

    private String normalizarNit(String nit) {
        if (nit == null) {
            return "";
        }
        return nit.replaceAll("\\D", "");
    }
}
