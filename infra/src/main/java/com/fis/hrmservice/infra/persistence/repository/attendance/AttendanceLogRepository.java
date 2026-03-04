package com.fis.hrmservice.infra.persistence.repository.attendance;

import com.fis.hrmservice.infra.persistence.entity.AttendanceLog;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceLogRepository extends JpaRepository<AttendanceLog, Long> {
  Optional<AttendanceLog> findByUser_IdAndWorkDate(Long userId, LocalDate workDate);

  List<AttendanceLog> findAllByUser_IdAndWorkDateOrderByCheckInTimeDesc(Long userId, LocalDate workDate);
}
