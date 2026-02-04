package com.fis.hrmservice.domain.usecase.command.ticket;

import java.time.LocalTime;

public class RemoteOnsiteRequest {
    LocalTime startTime;
    LocalTime endTime;
    String location;
}