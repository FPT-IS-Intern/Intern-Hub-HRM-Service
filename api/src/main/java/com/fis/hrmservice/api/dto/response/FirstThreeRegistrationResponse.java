package com.fis.hrmservice.api.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FirstThreeRegistrationResponse {
    String senderFullName;
    LocalDate registrationDate;
    String ticketStatus;
}
