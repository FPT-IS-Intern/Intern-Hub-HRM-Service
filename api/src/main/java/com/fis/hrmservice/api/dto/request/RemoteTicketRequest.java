package com.fis.hrmservice.api.dto.request;

import java.time.LocalTime;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RemoteTicketRequest {
  LocalTime startTime;
  LocalTime endTime;
  String location;
}
