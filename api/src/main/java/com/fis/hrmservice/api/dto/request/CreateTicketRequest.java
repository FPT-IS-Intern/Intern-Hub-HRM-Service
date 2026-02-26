package com.fis.hrmservice.api.dto.request;

import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateTicketRequest {
  String ticketType;
  LocalDate fromDate;
  LocalDate toDate;
  String reason;
  MultipartFile evidence;
}
