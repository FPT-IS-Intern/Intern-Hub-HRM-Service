package com.fis.hrmservice.domain.usecase.command.ticket;

import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

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

