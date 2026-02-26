package com.fis.hrmservice.domain.port.output.ticket.remoteticket;

import com.fis.hrmservice.domain.model.user.WorkLocationModel;
import java.util.List;

public interface WorkLocationRepositoryPort {
  boolean existByLocationName(String locationName);

  List<String> findAllLocationNames();

  WorkLocationModel findByLocationName(String locationName);
}
