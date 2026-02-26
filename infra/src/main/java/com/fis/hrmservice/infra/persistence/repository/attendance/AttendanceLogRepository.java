package com.fis.hrmservice.infra.persistence.repository.attendance;

import com.fis.hrmservice.infra.persistence.entity.AttendanceLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface AttendanceLogRepository extends JpaRepository<AttendanceLog, Long> {
    Optional<AttendanceLog> findByUser_IdAndWorkDate(Long userId, LocalDate workDate);
}
