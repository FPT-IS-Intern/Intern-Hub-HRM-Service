package com.fis.hrmservice.infra.persistence.adapter;

import com.fis.hrmservice.domain.model.user.UserModel;
import com.fis.hrmservice.domain.port.output.UserRepositoryPort;
import com.fis.hrmservice.infra.persistence.entity.UserEntity;
import com.fis.hrmservice.infra.persistence.repository.UserJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Adapter implementation for UserRepositoryPort.
 * This class bridges the domain layer with the infrastructure (JPA) layer.
 */
@Component
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final UserJpaRepository userJpaRepository;

    public UserRepositoryAdapter(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public UserModel save(UserModel user) {
        UserEntity entity = toEntity(user);
        UserEntity savedEntity = userJpaRepository.save(entity);
        return toModel(savedEntity);
    }

    @Override
    public Optional<UserModel> findById(Long userId) {
        return userJpaRepository.findById(userId)
                .map(this::toModel);
    }

    @Override
    public Optional<UserModel> findByEmail(String email) {
        return userJpaRepository.findByCompanyEmail(email)
                .map(this::toModel);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userJpaRepository.existsByCompanyEmail(email);
    }

    @Override
    public boolean existsByIdNumber(String idNumber) {
        return userJpaRepository.existsByIdNumber(idNumber);
    }

    // === Mapping methods ===

    private UserEntity toEntity(UserModel model) {
        UserEntity entity = new UserEntity();
        entity.setUserId(model.getUserId());
        entity.setPositionId(model.getPositionId());
        entity.setMentorId(model.getMentorId());
        entity.setFullName(model.getFullName());
        entity.setIdNumber(model.getIdNumber());
        entity.setDateOfBirth(model.getDateOfBirth());
        entity.setCompanyEmail(model.getCompanyEmail());
        entity.setPhoneNumber(model.getPhoneNumber());
        entity.setAddress(model.getAddress());
        entity.setInternshipStartDate(model.getInternshipStartDate());
        entity.setInternshipEndDate(model.getInternshipEndDate());
        entity.setStatus(model.getStatus());
        return entity;
    }

    private UserModel toModel(UserEntity entity) {
        return UserModel.builder()
                .userId(entity.getUserId())
                .positionId(entity.getPositionId())
                .mentorId(entity.getMentorId())
                .fullName(entity.getFullName())
                .idNumber(entity.getIdNumber())
                .dateOfBirth(entity.getDateOfBirth())
                .companyEmail(entity.getCompanyEmail())
                .phoneNumber(entity.getPhoneNumber())
                .address(entity.getAddress())
                .internshipStartDate(entity.getInternshipStartDate())
                .internshipEndDate(entity.getInternshipEndDate())
                .status(entity.getStatus())
                .build();
    }
}
