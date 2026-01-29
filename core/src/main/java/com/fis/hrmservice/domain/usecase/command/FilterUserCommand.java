package com.fis.hrmservice.domain.usecase.command;

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
    List<String> sysStatuses;
    List<String> roles;
    List<String> positions;
}