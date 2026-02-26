package com.fis.hrmservice.domain.usecase.command.user;

import com.fis.hrmservice.domain.model.constant.UserStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FilterUserCommand {
    String keyword;
    List<UserStatus> sysStatuses;
    List<String> roles;
    List<String> positions;
}
