package com.fis.hrmservice.infra.persistence.repository.quicknote;

import com.fis.hrmservice.infra.persistence.entity.QuickNote;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuickNoteRepository extends JpaRepository<QuickNote, Long> {
  List<QuickNote> findAllByInternId(Long internId);
}
