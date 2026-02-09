package com.fis.hrmservice.infra.mapper;

import com.fis.hrmservice.domain.model.user.AvatarModel;
import com.fis.hrmservice.infra.persistence.entity.Avatar;
import org.mapstruct.Mapper;

@Mapper(
    componentModel = "spring",
    uses = {UserMapper.class})
public interface AvatarMapper {
  AvatarModel toModel(Avatar avatar);
}
