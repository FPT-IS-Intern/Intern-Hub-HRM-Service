package com.fis.hrmservice.domain.model.ticket;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
public class LeaveRequestModel extends TicketModel {
    Long leaveTypeId;
    Integer totalDays;
    String status;
}