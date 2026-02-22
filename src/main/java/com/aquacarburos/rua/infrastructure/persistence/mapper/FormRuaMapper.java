package com.aquacarburos.rua.infrastructure.persistence.mapper;

import com.aquacarburos.rua.domain.model.FormRua;
import com.aquacarburos.rua.infrastructure.persistence.entity.FormRuaEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FormRuaMapper {

    FormRuaEntity toEntity(FormRua domain);

    @InheritInverseConfiguration
    FormRua toDomain(FormRuaEntity entity);

    // Eliminados campos relacionados con PDF
    // MapStruct mapeará automáticamente los campos restantes
}
