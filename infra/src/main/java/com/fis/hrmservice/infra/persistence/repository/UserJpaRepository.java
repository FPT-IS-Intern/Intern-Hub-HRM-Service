package com.fis.hrmservice.infra.persistence.repository;

import com.fis.hrmservice.infra.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * JPA Repository for UserEntity.
 */
@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByCompanyEmail(String companyEmail);

    boolean existsByCompanyEmail(String companyEmail);

    boolean existsByIdNumber(String idNumber);
}
