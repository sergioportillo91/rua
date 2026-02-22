package com.aquacarburos.rua.infrastructure.rest.mapper;

import com.aquacarburos.rua.application.dto.FormRuaRequest;
import com.aquacarburos.rua.application.dto.FormRuaResponse;
import com.aquacarburos.rua.domain.model.FormRua;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FormRuaRestMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    FormRua toDomain(FormRuaRequest request);

    FormRuaResponse toResponse(FormRua formRua);

    List<FormRuaResponse> toResponseList(List<FormRua> formRuaList);
}
