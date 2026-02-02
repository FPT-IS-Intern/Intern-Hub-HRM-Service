package com.fis.hrmservice.domain.model.ticket;

import com.fis.hrmservice.domain.model.common.BaseDomain;
import com.fis.hrmservice.domain.model.user.UserModel;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TicketModel extends BaseDomain {

    long ticketId;
    UserModel requester;
    TicketTypeModel ticketType;
    long startAt;
    long endAt;
    String reason;
    String sysStatus;
}
