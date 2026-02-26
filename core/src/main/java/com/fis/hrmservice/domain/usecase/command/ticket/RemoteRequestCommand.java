package com.fis.hrmservice.domain.usecase.command.ticket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RemoteRequestCommand {
    LocalTime startTime;
    LocalTime endTime;
    String location;
}
