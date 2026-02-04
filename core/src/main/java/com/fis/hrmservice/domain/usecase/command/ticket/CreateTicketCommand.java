package com.fis.hrmservice.domain.usecase.command.ticket;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateTicketCommand {
    String ticketType;
    LocalDate fromDate;
    LocalDate toDate;
    String reason;
    MultipartFile evidence;
}