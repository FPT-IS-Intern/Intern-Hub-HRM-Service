package com.fis.hrmservice.domain.usecase.implement.user;

import lombok.RequiredArgsConstructor;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import com.fis.hrmservice.domain.model.user.AvatarModel;
import com.fis.hrmservice.domain.model.user.UserModel;
import com.fis.hrmservice.domain.port.output.user.AvatarRepositoryPort;
import com.fis.hrmservice.domain.port.output.user.UserRepositoryPort;
import com.fis.hrmservice.domain.usecase.command.user.FilterUserCommand;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FilterUseCaseImpl {

    private final UserRepositoryPort userRepositoryPort;
    private final AvatarRepositoryPort avatarRepositoryPort;

    public List<UserModel> filterUsers(FilterUserCommand command) {
        List<UserModel> users = userRepositoryPort.filterUser(command);

        users.forEach(
                user -> {
                    AvatarModel avatarUrl = avatarRepositoryPort.getAvatarByUserId(user.getUserId());
                    user.setAvatar(avatarUrl);
                });

        return users;
    }
}

