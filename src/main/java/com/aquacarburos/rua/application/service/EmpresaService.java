package com.aquacarburos.rua.application.service;

import com.aquacarburos.rua.domain.model.Empresa;
import com.aquacarburos.rua.domain.model.RolNombre;
import com.aquacarburos.rua.domain.model.exception.EmpresaNotFoundException;
import com.aquacarburos.rua.domain.repository.EmpresaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    public Empresa crear(Empresa empresa) {
        RolNombre rol = empresa.getRol() != null ? empresa.getRol() : RolNombre.ROL_CLIENTE;
        empresa.setRol(rol);
        // Guardar la clave en texto plano
        return empresaRepository.save(empresa);
    }

    public List<Empresa> listarTodas() {
        return empresaRepository.findAll();
    }

    public Page<Empresa> listarTodas(Pageable pageable) {
        return empresaRepository.findAll(pageable);
    }

    public Empresa buscarPorId(Long id) {
        return empresaRepository.findById(id).orElseThrow(() -> new EmpresaNotFoundException(id));
    }

    public Empresa buscarPorNit(String nit) {
        return empresaRepository.findByNit(nit).orElseThrow(() -> new EmpresaNotFoundException(nit));
    }

    public Empresa actualizar(Long id, Empresa empresa) {
        Empresa empresaExistente = buscarPorId(id);
        empresaExistente.setNit(empresa.getNit());
        empresaExistente.setNombre(empresa.getNombre());
        empresaExistente.setUsuario(empresa.getUsuario() != null ? empresa.getUsuario() : empresaExistente.getUsuario());
        empresaExistente.setRol(empresa.getRol() != null ? empresa.getRol() : empresaExistente.getRol());
        // Guardar la clave en texto plano
        if (empresa.getClave() != null && !empresa.getClave().isBlank()) {
            empresaExistente.setClave(empresa.getClave());
        }

        empresaExistente.setPago(empresa.isPago());
        return empresaRepository.save(empresaExistente);
    }

}