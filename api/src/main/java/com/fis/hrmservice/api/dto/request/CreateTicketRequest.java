package com.fis.hrmservice.api.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateTicketRequest {
    String ticketType;
    LocalDate fromDate;
    LocalDate toDate;
    String reason;
    MultipartFile evidence;
}
