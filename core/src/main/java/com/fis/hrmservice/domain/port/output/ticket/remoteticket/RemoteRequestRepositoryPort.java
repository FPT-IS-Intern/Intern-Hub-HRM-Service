package com.fis.hrmservice.domain.port.output.ticket.remoteticket;

import com.fis.hrmservice.domain.model.ticket.RemoteRequestModel;

public interface RemoteRequestRepositoryPort {

    RemoteRequestModel save(RemoteRequestModel remoteRequest);
}
