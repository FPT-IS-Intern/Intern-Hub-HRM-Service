package com.fis.hrmservice.domain.usecase.command.ticket;

import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RemoteRequestCommand {
    LocalTime startTime;
    LocalTime endTime;
    String location;
}
