package com.fis.hrmservice.domain.usecase.attendance;

import com.fis.hrmservice.domain.model.attendance.AttendanceLogModel;
import com.fis.hrmservice.domain.model.attendance.AttendanceStatusModel;
import com.fis.hrmservice.domain.usecase.command.attendance.CheckInCommand;
import com.fis.hrmservice.domain.usecase.command.attendance.CheckOutCommand;

import java.time.LocalDate;

public interface AttendanceUseCase {
    AttendanceStatusModel getAttendanceStatus(Long userId, LocalDate workDate);

    AttendanceLogModel checkIn(CheckInCommand command);

    AttendanceLogModel checkOut(CheckOutCommand command);
}
