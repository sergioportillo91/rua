package com.aquacarburos.rua.infrastructure.rest.mapper;

import com.aquacarburos.rua.application.dto.EmpresaRequest;
import com.aquacarburos.rua.application.dto.EmpresaResponse;
import com.aquacarburos.rua.domain.model.Empresa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.IterableMapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmpresaRestMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    Empresa toDomain(EmpresaRequest request);

    @Named("toResponse")
    default EmpresaResponse toResponse(Empresa empresa) {
        EmpresaResponse response = new EmpresaResponse();
        response.setId(empresa.getId());
        response.setNit(empresa.getNit());
        response.setNombre(empresa.getNombre());
        response.setUsuario(empresa.getUsuario());
        response.setRol(empresa.getRol() != null ? empresa.getRol().name() : null);
        response.setPago(empresa.isPago());
        response.setFechaCreacion(empresa.getFechaCreacion());
        response.setFechaModificacion(empresa.getFechaModificacion());
        // Devolver la clave en texto plano
        response.setClave(empresa.getClave());
        return response;
    }

    @IterableMapping(qualifiedByName = "toResponse")
    List<EmpresaResponse> toResponseList(List<Empresa> empresas);
}