package com.fis.hrmservice.infra.persistence.adapter.user;

import com.fis.hrmservice.domain.model.user.AvatarModel;
import com.fis.hrmservice.domain.port.output.user.AvatarRepositoryPort;
import com.fis.hrmservice.infra.mapper.AvatarMapper;
import com.fis.hrmservice.infra.persistence.entity.Avatar;
import com.fis.hrmservice.infra.persistence.repository.user.AvatarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class AvatarRepositoryAdapter implements AvatarRepositoryPort {

  private final AvatarRepository avatarRepository;

  private final AvatarMapper avatarMapper;

  @Override
  public AvatarModel getAvatarByUserId(Long userId) {
    return avatarMapper.toModel(avatarRepository.findAvatarByUserId(userId));
  }

  @Override
  public AvatarModel save(AvatarModel avatar) {
    return avatarMapper.toModel(avatarRepository.save(avatarMapper.toEntity(avatar)));
  }

  @Override
  public Map<Long, AvatarModel> getAvatarsByUserIds(List<Long> userIds) {

    if (userIds == null || userIds.isEmpty()) {
      return Collections.emptyMap();
    }

    List<Avatar> avatars = avatarRepository.findByUserUserIdIn(userIds);

    return avatars.stream()
            .collect(Collectors.toMap(
                    avatar -> avatar.getUser().getId(),
                    avatarMapper::toModel
            ));
  }
}
