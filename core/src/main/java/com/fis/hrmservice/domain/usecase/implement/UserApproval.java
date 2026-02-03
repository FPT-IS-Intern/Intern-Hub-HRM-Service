package com.fis.hrmservice.domain.usecase.implement;

import com.fis.hrmservice.domain.model.constant.UserStatus;
import com.fis.hrmservice.domain.model.user.UserModel;
import com.fis.hrmservice.domain.port.input.ApprovalUser;
import com.fis.hrmservice.domain.port.output.UserRepositoryPort;
import com.intern.hub.library.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserApproval implements ApprovalUser {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public void approveUser(Long userId) {
        UserModel userModel = userRepositoryPort.findById(userId).get();

        if (userModel == null) {
            throw new NotFoundException("User not found with id: " + userId);
        }

        userModel.setSysStatus(UserStatus.APPROVED);
    }
}
