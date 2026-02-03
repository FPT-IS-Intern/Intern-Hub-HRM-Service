package com.fis.hrmservice.domain.port.input;

import com.fis.hrmservice.domain.model.user.UserModel;

public interface RejectionUser {
    UserModel rejectUser(Long userId);
}
