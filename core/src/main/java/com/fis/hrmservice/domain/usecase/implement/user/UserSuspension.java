package com.fis.hrmservice.domain.usecase.implement.user;

import com.fis.hrmservice.domain.model.constant.UserStatus;
import com.fis.hrmservice.domain.model.user.UserModel;
import com.fis.hrmservice.domain.port.output.user.UserRepositoryPort;
import com.intern.hub.library.common.exception.ConflictDataException;
import com.intern.hub.library.common.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserSuspension {

    @Autowired
    private UserRepositoryPort userRepositoryPort;

    @Transactional
    @Modifying
    public UserModel suspendUser(Long userId) {

        UserModel model = userRepositoryPort.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));

        if (model.getSysStatus().equals(UserStatus.SUSPENDED)) {
            throw new ConflictDataException("User is already suspended");
        }

        Long suspended = userRepositoryPort.suspendUser(userId, UserStatus.SUSPENDED);

        if (suspended != 1) {
            throw new ConflictDataException("Cannot suspend user");
        }

        return model;
    }
}
