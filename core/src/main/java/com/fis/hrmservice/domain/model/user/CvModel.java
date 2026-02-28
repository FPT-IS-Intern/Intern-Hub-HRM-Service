package com.fis.hrmservice.domain.model.user;

import com.fis.hrmservice.domain.model.common.BaseDomain;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CvModel extends BaseDomain {
  long cvId;
  UserModel user;
  String cvUrl;
  String fileType;
  long fileSize;
  String fileName;
}
