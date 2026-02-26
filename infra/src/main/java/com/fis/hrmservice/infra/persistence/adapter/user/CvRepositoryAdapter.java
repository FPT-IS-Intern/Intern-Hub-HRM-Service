package com.fis.hrmservice.infra.persistence.adapter.user;

import lombok.RequiredArgsConstructor;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import com.fis.hrmservice.domain.model.user.CvModel;
import com.fis.hrmservice.domain.port.output.user.CvRepositoryPort;
import com.fis.hrmservice.infra.mapper.CvMapper;
import com.fis.hrmservice.infra.persistence.repository.user.CvRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CvRepositoryAdapter implements CvRepositoryPort {
    private final CvRepository cvRepository;

    private final CvMapper cvMapper;

    @Override
    public CvModel save(CvModel cv) {
        return cvMapper.toModel(cvRepository.save(cvMapper.toEntity(cv)));
    }
}

