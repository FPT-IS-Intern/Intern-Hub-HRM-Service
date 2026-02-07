package com.fis.hrmservice.infra.persistence.adapter.ticket.remote;

import com.fis.hrmservice.domain.model.ticket.RemoteRequestModel;
import com.fis.hrmservice.domain.port.output.ticket.remoteticket.RemoteRequestRepositoryPort;
import com.fis.hrmservice.infra.mapper.RemoteRequestMapper;
import com.fis.hrmservice.infra.persistence.entity.RemoteRequest;
import com.fis.hrmservice.infra.persistence.repository.ticket.RemoteTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RemoteRequestAdapter implements RemoteRequestRepositoryPort {

    @Autowired
    private RemoteRequestMapper remoteRequestMapper;

    @Autowired
    private RemoteTicketRepository remoteTicketRepository;

    @Override
    public RemoteRequestModel save(RemoteRequestModel remoteRequest) {

        RemoteRequest request = remoteRequestMapper.toEntity(remoteRequest);

        return remoteRequestMapper.toModel(remoteTicketRepository.save(request));
    }
}
