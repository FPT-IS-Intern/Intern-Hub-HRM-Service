package com.fis.hrmservice.api.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RemoteTicketRequest {
    LocalTime startTime;
    LocalTime endTime;
    String location;
}
