package com.fis.hrmservice.domain.model.ticket;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

/**
 * Domain model for RemoteRequest entity.
 * Extends TicketModel as it shares ticket_id with tickets table.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
public class RemoteRequestModel extends TicketModel {
    Long workLocationId;
    LocalDateTime startTime;
    LocalDateTime endTime;
}