package com.fis.hrmservice.infra.mapper;

import com.fis.hrmservice.domain.model.user.UserModel;
import com.fis.hrmservice.infra.persistence.entity.Position;
import com.fis.hrmservice.infra.persistence.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "userId", source = "id")
    @Mapping(target = "positionId", source = "position", qualifiedByName = "positionToId")
    @Mapping(target = "mentorId", source = "mentor", qualifiedByName = "mentorToId")
    UserModel toModel(User entity);

    @Mapping(target = "id", source = "userId")
    @Mapping(target = "position", source = "positionId", qualifiedByName = "idToPosition")
    @Mapping(target = "mentor", source = "mentorId", qualifiedByName = "idToMentor")
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    User toEntity(UserModel model);

    /* ===================== CUSTOM MAPPINGS ===================== */

    @Named("positionToId")
    default Long positionToId(Position position) {
        return position != null ? position.getId() : null;
    }

    @Named("mentorToId")
    default Long mentorToId(User mentor) {
        return mentor != null ? mentor.getId() : null;
    }

    @Named("idToPosition")
    default Position idToPosition(Long positionId) {
        if (positionId == null) return null;
        Position position = new Position();
        position.setId(positionId);
        return position;
    }

    @Named("idToMentor")
    default User idToMentor(Long mentorId) {
        if (mentorId == null) return null;
        User mentor = new User();
        mentor.setId(mentorId);
        return mentor;
    }
}
