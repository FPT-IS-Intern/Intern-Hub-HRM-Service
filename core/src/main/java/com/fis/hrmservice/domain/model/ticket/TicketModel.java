package com.fis.hrmservice.domain.model.ticket;

import com.fis.hrmservice.domain.model.constant.TicketStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

/**
 * Domain model for Ticket entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TicketModel {
    Long ticketId;
    Long userId;
    Long ticketTypeId;
    LocalDate startAt;
    LocalDate endAt;
    String reason;
    TicketStatus status;
}
