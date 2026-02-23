package com.fis.hrmservice.infra.persistence.adapter.user;

import com.fis.hrmservice.domain.model.user.CvModel;
import com.fis.hrmservice.domain.port.output.user.CvRepositoryPort;
import com.fis.hrmservice.infra.mapper.CvMapper;
import com.fis.hrmservice.infra.persistence.repository.user.CvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CvRepositoryAdapter implements CvRepositoryPort {
  @Autowired private CvRepository cvRepository;

  @Autowired private CvMapper cvMapper;

  @Override
  public CvModel save(CvModel cv) {
    return cvMapper.toModel(cvRepository.save(cvMapper.toEntity(cv)));
  }
}
