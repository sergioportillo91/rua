package com.aquacarburos.rua.infrastructure.persistence.mapper;

import com.aquacarburos.rua.domain.model.Empresa;
import com.aquacarburos.rua.infrastructure.persistence.entity.EmpresaEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface EmpresaMapper {

    EmpresaEntity toEntity(Empresa domain);

    @InheritInverseConfiguration
    Empresa toDomain(EmpresaEntity entity);
}
