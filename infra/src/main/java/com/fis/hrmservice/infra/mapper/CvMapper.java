package com.fis.hrmservice.infra.mapper;

import com.fis.hrmservice.domain.model.user.CvModel;
import com.fis.hrmservice.domain.model.user.UserModel;
import com.fis.hrmservice.infra.persistence.entity.Cv;
import com.fis.hrmservice.infra.persistence.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CvMapper {

  // ===== Entity -> Model =====
  @Mapping(target = "cvId", source = "id")
  @Mapping(target = "fileName", source = "cvFileName")
  @Mapping(target = "user", source = "user", qualifiedByName = "userToSimpleModel")
  CvModel toModel(Cv cv);

  // ===== Model -> Entity =====
  @Mapping(target = "id", source = "cvId")
  @Mapping(target = "cvFileName", source = "fileName")
  @Mapping(target = "user", source = "user", qualifiedByName = "userModelToEntity")
  @Mapping(target = "createdAt", source = "createdAt")
  @Mapping(target = "updatedAt", source = "updatedAt")
  @Mapping(target = "createdBy", source = "createdBy")
  @Mapping(target = "updatedBy", source = "updatedBy")
  @Mapping(target = "version", source = "version")
  Cv toEntity(CvModel cvModel);

  @Named("userToSimpleModel")
  default UserModel userToSimpleModel(User user) {
    if (user == null) return null;
    return UserModel.builder().userId(user.getId()).build();
  }

  @Named("userModelToEntity")
  default User userModelToEntity(UserModel userModel) {
    if (userModel == null || userModel.getUserId() == null) return null;
    User user = new User();
    user.setId(userModel.getUserId());
    return user;
  }
}
