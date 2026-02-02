package com.fis.hrmservice.domain.port.input;

import com.fis.hrmservice.domain.model.user.UserModel;

public interface UserProfileUseCase {
    UserModel getUserProfile(Long userId);
}
