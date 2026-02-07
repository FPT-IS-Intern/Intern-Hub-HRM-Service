package com.fis.hrmservice.infra.mapper;

import com.fis.hrmservice.domain.model.user.WorkLocationModel;
import com.fis.hrmservice.infra.persistence.entity.WorkLocation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WorkLocationMapper {

    @Mapping(source = "id", target = "workLocationId")
    WorkLocationModel toModel(WorkLocation entity);

    @Mapping(source = "workLocationId", target = "id")
    WorkLocation toEntity(WorkLocationModel model);
}
