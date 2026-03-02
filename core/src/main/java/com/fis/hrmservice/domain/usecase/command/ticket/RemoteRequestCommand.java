package com.fis.hrmservice.domain.usecase.command.ticket;

import java.time.LocalTime;
import lombok.*;

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
