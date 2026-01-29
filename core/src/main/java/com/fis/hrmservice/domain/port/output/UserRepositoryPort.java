package com.fis.hrmservice.domain.port.output;

import com.fis.hrmservice.domain.model.user.UserModel;

import java.util.Optional;

public interface UserRepositoryPort {
    UserModel save(UserModel user);
    Optional<UserModel> findById(Long userId);
    Optional<UserModel> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByIdNumber(String idNumber);
}