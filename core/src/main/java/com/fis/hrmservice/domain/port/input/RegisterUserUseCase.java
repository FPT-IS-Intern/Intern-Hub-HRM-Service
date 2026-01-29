package com.fis.hrmservice.domain.port.input;

import com.fis.hrmservice.domain.model.user.UserModel;
import com.fis.hrmservice.domain.usecase.command.RegisterUserCommand;

public interface RegisterUserUseCase {
    UserModel registerUser(RegisterUserCommand command);
}
