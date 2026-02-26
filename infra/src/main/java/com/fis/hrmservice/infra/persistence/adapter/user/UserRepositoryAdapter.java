package com.fis.hrmservice.infra.persistence.adapter.user;

import com.fis.hrmservice.domain.model.constant.UserStatus;
import com.fis.hrmservice.domain.model.user.UserModel;
import com.fis.hrmservice.domain.port.output.user.UserRepositoryPort;
import com.fis.hrmservice.domain.usecase.command.user.FilterUserCommand;
import com.fis.hrmservice.infra.mapper.UserMapper;
import com.fis.hrmservice.infra.persistence.entity.User;
import com.fis.hrmservice.infra.persistence.repository.user.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final UserJpaRepository userJpaRepository;

    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserModel save(UserModel user) {
        User entity = userMapper.toEntity(user);

        // Fix bidirectional relationship: avatarToEntity/cvToEntity ignore the user field,
        // so we must set it manually to prevent cascade from nullifying the FK.
        if (entity.getAvatar() != null) {
            entity.getAvatar().setUser(entity);
        }
        if (entity.getCv() != null) {
            entity.getCv().setUser(entity);
        }

        User savedEntity = userJpaRepository.save(entity);
        return userMapper.toModel(savedEntity);
    }

    @Override
    public Optional<UserModel> findById(Long userId) {
        return Optional.ofNullable(userMapper.toModel(userJpaRepository.findById(userId).get()));
    }

    @Override
    public Optional<UserModel> findByEmail(String email) {
        return Optional.ofNullable(
                userMapper.toModel(userJpaRepository.findByCompanyEmail(email).get()));
    }

    @Override
    public boolean existsByEmail(String email) {
        return userJpaRepository.existsByCompanyEmail(email);
    }

    @Override
    public boolean existsByIdNumber(String idNumber) {
        return userJpaRepository.existsByIdNumber(idNumber);
    }

    @Override
    public List<UserModel> findAll() {
        return List.of();
    }

    @Override
    public List<UserModel> filterUser(FilterUserCommand command) {
        return userMapper.toResponseList(userJpaRepository.filterUser(command));
    }

    @Override
    public Long updateStatus(Long userId, UserStatus status) {
        return userJpaRepository.updateStatus(userId, status);
    }

    @Override
    public UserModel internalUserProfile(Long userId) {
        return userMapper.toModel(userJpaRepository.internalUserProfile(userId));
    }

    @Override
    public Long suspendUser(Long userId, UserStatus status) {
        return userJpaRepository.suspendUser(userId, status);
    }

    @Override
    public int totalIntern() {
        return userJpaRepository.totalIntern();
    }

    @Override
    public int internshipChanging() {
        return userJpaRepository.internshipChanging();
    }
}
