package com.aquacarburos.rua.infrastructure.persistence.mapper;

import com.aquacarburos.rua.domain.model.Rol;
import com.aquacarburos.rua.infrastructure.persistence.entity.RolEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RolMapper {

    RolEntity toEntity(Rol domain);

    @InheritInverseConfiguration
    Rol toDomain(RolEntity entity);
}
