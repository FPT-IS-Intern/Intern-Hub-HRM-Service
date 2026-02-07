package com.fis.hrmservice.api.dto.request;

import java.util.List;

import com.fis.hrmservice.domain.model.constant.UserStatus;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FilterRequest {
  String keyword;
  List<UserStatus> sysStatuses;
  List<String> roles;
  List<String> positions;
}
