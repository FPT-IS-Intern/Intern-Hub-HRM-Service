package com.fis.hrmservice.infra.persistence.repository.user;

import com.fis.hrmservice.infra.persistence.entity.Cv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CvRepository extends JpaRepository<Cv, Long> {}
