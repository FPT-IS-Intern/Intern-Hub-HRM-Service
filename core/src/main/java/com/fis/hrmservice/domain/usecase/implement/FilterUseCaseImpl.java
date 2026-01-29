package com.fis.hrmservice.domain.usecase.implement;

import com.fis.hrmservice.domain.model.user.UserModel;
import com.fis.hrmservice.domain.port.input.FilterUserUseCase;

import java.util.List;

public class FilterUseCaseImpl implements FilterUserUseCase {
    @Override
    public List<UserModel> filterUserIdsByDepartmentIds(List<Long> departmentIds) {
        return List.of();
    }
}
