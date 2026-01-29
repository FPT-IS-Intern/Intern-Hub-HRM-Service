package com.fis.hrmservice.infra.persistence.repository;

import com.fis.hrmservice.infra.persistence.entity.PositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * JPA Repository for PositionEntity.
 */
@Repository
public interface PositionJpaRepository extends JpaRepository<PositionEntity, Long> {

    Optional<PositionEntity> findByName(String name);
}
