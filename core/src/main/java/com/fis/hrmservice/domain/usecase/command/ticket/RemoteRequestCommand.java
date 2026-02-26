package com.fis.hrmservice.domain.usecase.command.ticket;

import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
