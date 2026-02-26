package com.fis.hrmservice.domain.model.attendance;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder
public class AttendanceStatusModel {
    private LocalDate workDate;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;
    private boolean isCheckInValid;
    private boolean isCheckOutValid;
    private String checkInMessage;
    private String checkOutMessage;
}
