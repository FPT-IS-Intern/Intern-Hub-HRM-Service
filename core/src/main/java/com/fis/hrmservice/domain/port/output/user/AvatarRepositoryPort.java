package com.fis.hrmservice.domain.port.output.user;

import com.fis.hrmservice.domain.model.user.AvatarModel;

import java.util.List;
import java.util.Map;

public interface AvatarRepositoryPort {
  AvatarModel getAvatarByUserId(Long userId);

  AvatarModel save(AvatarModel avatar);
  Map<Long, AvatarModel> getAvatarsByUserIds(List<Long> userIds);
}
