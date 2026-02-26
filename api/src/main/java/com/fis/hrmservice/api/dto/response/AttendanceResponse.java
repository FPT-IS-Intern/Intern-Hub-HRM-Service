package com.fis.hrmservice.api.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AttendanceResponse {
  private Long attendanceId;
  private String attendanceStatus;
  private long checkInTime;
  private long checkOutTime;
  private boolean isCheckInValid;
  private boolean isCheckOutValid;
  private String message;
}
