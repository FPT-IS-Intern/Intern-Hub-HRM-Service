package com.fis.hrmservice.domain.usecase.implement.user;

import com.fis.hrmservice.domain.model.user.AvatarModel;
import com.fis.hrmservice.domain.model.user.UserModel;
import com.fis.hrmservice.domain.port.output.user.AvatarRepositoryPort;
import com.fis.hrmservice.domain.port.output.user.UserRepositoryPort;
import com.fis.hrmservice.domain.usecase.command.user.FilterUserCommand;
import com.intern.hub.library.common.dto.PaginatedData;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FilterUseCaseImpl {

    private final UserRepositoryPort userRepositoryPort;
    private final AvatarRepositoryPort avatarRepositoryPort;

    public PaginatedData<UserModel> filterUsers(
            FilterUserCommand command,
            int page,
            int size
    ) {

        PaginatedData<UserModel> pagedUsers =
                userRepositoryPort.filterUser(command, page, size);

        if (pagedUsers.getItems().isEmpty()) {
            return PaginatedData.empty();
        }

        List<Long> userIds = pagedUsers.getItems()
                .stream()
                .map(UserModel::getUserId)
                .toList();

        Map<Long, AvatarModel> avatarMap =
                avatarRepositoryPort.getAvatarsByUserIds(userIds);

        pagedUsers.getItems().forEach(user ->
                user.setAvatar(avatarMap.get(user.getUserId()))
        );

        return pagedUsers;
    }
}
