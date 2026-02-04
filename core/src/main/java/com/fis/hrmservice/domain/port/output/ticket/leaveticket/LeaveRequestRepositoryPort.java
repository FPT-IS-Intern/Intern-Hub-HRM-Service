package com.fis.hrmservice.domain.port.output.ticket.leaveticket;

import com.fis.hrmservice.domain.model.ticket.LeaveRequestModel;
import com.fis.hrmservice.domain.model.user.UserModel;

public interface LeaveRequestRepositoryPort {
    LeaveRequestModel save(LeaveRequestModel user);
}
