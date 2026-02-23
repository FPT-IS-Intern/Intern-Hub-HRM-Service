package com.fis.hrmservice.infra.persistence.adapter.user;

import com.fis.hrmservice.domain.model.user.AvatarModel;
import com.fis.hrmservice.domain.port.output.user.AvatarRepositoryPort;
import com.fis.hrmservice.infra.mapper.AvatarMapper;
import com.fis.hrmservice.infra.persistence.repository.user.AvatarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AvatarRepositoryAdapter implements AvatarRepositoryPort {

  @Autowired private AvatarRepository avatarRepository;

  @Autowired private AvatarMapper avatarMapper;

  @Override
  public AvatarModel getAvatarByUserId(Long userId) {
    return avatarMapper.toModel(avatarRepository.findAvatarByUserId(userId));
  }

  @Override
  public AvatarModel save(AvatarModel avatar) {
    return avatarMapper.toModel(avatarRepository.save(avatarMapper.toEntity(avatar)));
  }
}
