package com.fis.hrmservice.domain.usecase.implement;

import com.fis.hrmservice.domain.model.user.UserModel;
import com.fis.hrmservice.domain.port.input.UserProfileUseCase;
import com.fis.hrmservice.domain.port.output.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserProfileUseCaseImpl implements UserProfileUseCase {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public UserModel getUserProfile(Long userId) {
        return userRepositoryPort.findById(userId).get();
    }
}
