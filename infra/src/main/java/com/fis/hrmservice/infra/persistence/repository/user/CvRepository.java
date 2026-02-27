package com.fis.hrmservice.infra.persistence.repository.user;

import com.fis.hrmservice.infra.persistence.entity.Cv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CvRepository extends JpaRepository<Cv, Long> {


    @Query("SELECT c FROM Cv c WHERE c.user.id = :userId")
    Cv findCvByUserId(@Param("userId") Long userId);
}
