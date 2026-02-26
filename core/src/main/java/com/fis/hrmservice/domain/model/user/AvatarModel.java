package com.fis.hrmservice.domain.model.user;

import com.fis.hrmservice.domain.model.common.BaseDomain;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class AvatarModel extends BaseDomain {
  long avatarId;
  UserModel user;
  String avatarUrl;
  String fileType;
  long fileSize;
  String fileName;
}
