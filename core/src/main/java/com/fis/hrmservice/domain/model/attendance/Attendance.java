package com.fis.hrmservice.domain.model.attendance;

import com.fis.hrmservice.domain.model.constant.AttendanceStatus;
import com.fis.hrmservice.domain.model.constant.SourceAttendance;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Attendance {
    Long attendanceId;
    Long userId;
    LocalDate workDate;
    LocalDateTime checkInTime;
    LocalDateTime checkOutTime;
    AttendanceStatus attendanceStatus;
    SourceAttendance source;
    String status;
}