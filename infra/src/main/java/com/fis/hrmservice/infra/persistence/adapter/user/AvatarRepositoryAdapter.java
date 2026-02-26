package com.fis.hrmservice.infra.persistence.adapter.user;

import com.fis.hrmservice.domain.model.user.AvatarModel;
import com.fis.hrmservice.domain.port.output.user.AvatarRepositoryPort;
import com.fis.hrmservice.infra.mapper.AvatarMapper;
import com.fis.hrmservice.infra.persistence.repository.user.AvatarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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
}
