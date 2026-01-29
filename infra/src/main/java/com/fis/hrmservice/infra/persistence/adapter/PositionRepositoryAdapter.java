package com.fis.hrmservice.infra.persistence.adapter;

import com.fis.hrmservice.domain.model.user.Position;
import com.fis.hrmservice.domain.port.output.PositionRepositoryPort;
import com.fis.hrmservice.infra.persistence.entity.PositionEntity;
import com.fis.hrmservice.infra.persistence.repository.PositionJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PositionRepositoryAdapter implements PositionRepositoryPort {

    private final PositionJpaRepository positionJpaRepository;

    public PositionRepositoryAdapter(PositionJpaRepository positionJpaRepository) {
        this.positionJpaRepository = positionJpaRepository;
    }

    @Override
    public Optional<Position> findByCode(String positionCode) {
        return positionJpaRepository.findByName(positionCode)
                .map(this::toModel);
    }

    @Override
    public Optional<Position> findById(Long positionId) {
        return positionJpaRepository.findById(positionId)
                .map(this::toModel);
    }

    private Position toModel(PositionEntity entity) {
        return Position.builder()
                .positionId(entity.getPositionId())
                .name(entity.getName())
                .description(entity.getDescription())
                .status(entity.getStatus())
                .build();
    }
}
