package com.fis.hrmservice.domain.port.output;

import com.fis.hrmservice.domain.model.user.Position;

import java.util.Optional;

public interface PositionRepositoryPort {
    Optional<Position> findByCode(String positionCode);
    Optional<Position> findById(Long positionId);
}
