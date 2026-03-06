package com.fis.hrmservice.domain.port.output.attendance;

import com.fis.hrmservice.domain.model.attendance.AttendanceLogModel;
import com.fis.hrmservice.domain.usecase.command.attendance.FilterAttendanceCommand;
import com.intern.hub.library.common.dto.PaginatedData;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AttendanceRepositoryPort {
  AttendanceLogModel save(AttendanceLogModel attendanceLog);

  Optional<AttendanceLogModel> findByUserIdAndDate(Long userId, LocalDate workDate);

  AttendanceLogModel update(AttendanceLogModel attendanceLog);

  PaginatedData<AttendanceLogModel> filterAttendanceLogs(FilterAttendanceCommand command, int page, int size);

  Long getCheckInOnTimePercent();

  Long getCheckInLateTimePercent();

  Long getNotAttendancePercent();

  List<AttendanceLogModel> filterAttendance(String keyword, String attendanceStatus);
}
