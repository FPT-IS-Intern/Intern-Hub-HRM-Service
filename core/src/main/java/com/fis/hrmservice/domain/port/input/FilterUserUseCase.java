package com.fis.hrmservice.domain.port.input;

import com.fis.hrmservice.domain.model.user.UserModel;

import java.util.List;

public interface FilterUserUseCase {
    List<UserModel> filterUserIdsByDepartmentIds(List<Long> departmentIds);
}