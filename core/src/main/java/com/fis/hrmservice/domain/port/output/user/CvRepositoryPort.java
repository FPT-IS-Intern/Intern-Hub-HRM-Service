package com.fis.hrmservice.domain.port.output.user;

import com.fis.hrmservice.domain.model.user.CvModel;

public interface CvRepositoryPort {
  CvModel save(CvModel cv);
  CvModel findByUserId(Long userId);
}
