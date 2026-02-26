package com.fis.hrmservice.domain.usecase.command.ticket;

import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RemoteRequestCommand {
  LocalTime startTime;
  LocalTime endTime;
  String location;
}
