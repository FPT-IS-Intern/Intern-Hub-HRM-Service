package com.fis.hrmservice.infra.mapper;

import com.fis.hrmservice.domain.model.user.AvatarModel;
import com.fis.hrmservice.domain.model.user.CvModel;
import com.fis.hrmservice.domain.model.user.PositionModel;
import com.fis.hrmservice.domain.model.user.UserModel;
import com.fis.hrmservice.infra.persistence.entity.Avatar;
import com.fis.hrmservice.infra.persistence.entity.Cv;
import com.fis.hrmservice.infra.persistence.entity.Position;
import com.fis.hrmservice.infra.persistence.entity.User;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import org.mapstruct.*;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface UserMapper {

  /* ===================== ENTITY -> MODEL ===================== */

  @Mapping(target = "userId", source = "id")
  @Mapping(target = "mentor", qualifiedByName = "mentorToModel")
  @Mapping(target = "position", qualifiedByName = "positionToModel")
  @Mapping(target = "avatar", qualifiedByName = "avatarToModel")
  @Mapping(target = "cv", qualifiedByName = "cvToModel")
  UserModel toModel(User entity);

  List<UserModel> toModelList(List<User> entities);

  /* ===================== MODEL -> ENTITY ===================== */

  @Mapping(target = "id", source = "userId")
  @Mapping(target = "mentor", qualifiedByName = "mentorToEntity")
  @Mapping(target = "position", source = "position", qualifiedByName = "positionToEntity")
  @Mapping(target = "dateOfBirth", source = "dateOfBirth")
  @Mapping(target = "avatar", qualifiedByName = "avatarToEntity")
  @Mapping(target = "cv", qualifiedByName = "cvToEntity")
  User toEntity(UserModel model);

  List<UserModel> toResponseList(List<User> users);

  /* ===================== CUSTOM ===================== */

  @Named("localDateToEpoch")
  default long localDateToEpoch(LocalDate date) {
    return date == null ? 0L : date.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
  }

  @Named("mentorToModel")
  default UserModel mentorToModel(User mentor) {
    if (mentor == null) return null;
    return UserModel.builder().userId(mentor.getId()).fullName(mentor.getFullName()).build();
  }

  @Named("mentorToEntity")
  default User mentorToEntity(UserModel mentor) {
    if (mentor == null || mentor.getUserId() == null) return null;
    User user = new User();
    user.setId(mentor.getUserId());
    return user;
  }

  @Named("positionToEntity")
  default Position positionToEntity(PositionModel model) {
    if (model == null || model.getPositionId() == null) return null;
    Position p = new Position();
    p.setId(model.getPositionId());
    return p;
  }

  @Named("positionToModel")
  default PositionModel positionToModel(Position entity) {
    if (entity == null) return null;
    return PositionModel.builder()
        .positionId(entity.getId())
        .name(entity.getName())
        .description(entity.getDescription())
        .build();
  }

  @Named("epochMillisToLocalDate")
  default LocalDate epochMillisToLocalDate(Long value) {
    if (value == null) return null;
    return Instant.ofEpochMilli(value).atZone(ZoneId.systemDefault()).toLocalDate();
  }

  @Named("avatarToModel")
  @Mapping(target = "user", ignore = true)
  @Mapping(target = "avatarId", source = "id")
  @Mapping(target = "fileName", source = "avatarFileName")
  AvatarModel avatarToModel(Avatar entity);

  @Named("cvToModel")
  @Mapping(target = "user", ignore = true)
  @Mapping(target = "cvId", source = "id")
  @Mapping(target = "fileName", source = "cvFileName")
  CvModel cvToModel(Cv entity);

  @Named("avatarToEntity")
  @Mapping(target = "user", ignore = true)
  @Mapping(target = "id", source = "avatarId")
  @Mapping(target = "avatarFileName", source = "fileName")
  Avatar avatarToEntity(AvatarModel model);

  @Named("cvToEntity")
  @Mapping(target = "user", ignore = true)
  @Mapping(target = "id", source = "cvId")
  @Mapping(target = "cvFileName", source = "fileName")
  Cv cvToEntity(CvModel model);
}
