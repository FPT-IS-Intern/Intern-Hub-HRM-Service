package com.fis.hrmservice.domain.usecase.implement.user;

import com.fis.hrmservice.domain.model.constant.UserStatus;
import com.fis.hrmservice.domain.model.user.UserModel;
import com.fis.hrmservice.domain.port.output.user.UserRepositoryPort;
import com.intern.hub.library.common.exception.ConflictDataException;
import com.intern.hub.library.common.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserApproval {

  @Autowired private UserRepositoryPort userRepositoryPort;

  @Transactional
  public UserModel approveUser(Long userId) {

    Long updated = userRepositoryPort.updateStatus(userId, UserStatus.APPROVED);

    if (updated != 1) {
      throw new ConflictDataException("Cannot approve user");
    }

    return userRepositoryPort
        .findById(userId)
        .orElseThrow(() -> new NotFoundException("User not found"));
  }
}
