package com.fis.hrmservice.domain.usecase.command.attendance;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Builder
@Setter
public class FilterAttendanceCommand {
    String nameOrEmail;
    String attendanceStatus;
}
