package com.fis.hrmservice.domain.model.attendance;

import com.fis.hrmservice.domain.model.common.BaseDomain;
import com.fis.hrmservice.domain.model.user.UserModel;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AttendanceLogModel extends BaseDomain {

    long attendanceId;
    UserModel user;
    LocalDate workDate;
    long checkInTime;
    long checkOutTime;
    String attendanceStatus;
    String source;
    boolean isCheckInValid;
    boolean isCheckOutValid;
}
