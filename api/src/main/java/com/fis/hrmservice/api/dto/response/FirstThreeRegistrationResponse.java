package com.fis.hrmservice.api.dto.response;

import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FirstThreeRegistrationResponse {
  String senderFullName;
  LocalDate registrationDate;
  String ticketStatus;
}
