package com.fis.hrmservice.api.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class AttendanceStatusResponse {
    private LocalDate workDate;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;
    private boolean isCheckInValid;
    private boolean isCheckOutValid;
    private String checkInMessage;
    private String checkOutMessage;
}
