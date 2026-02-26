package com.fis.hrmservice.infra.persistence.adapter.ticket.remote;

import com.fis.hrmservice.domain.model.ticket.TicketTypeModel;
import com.fis.hrmservice.domain.port.output.ticket.TicketTypeRepositoryPort;
import com.fis.hrmservice.infra.mapper.TicketTypeMapper;
import com.fis.hrmservice.infra.persistence.repository.ticket.TicketTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TicketTypeRepositoryAdapter implements TicketTypeRepositoryPort {

  private final TicketTypeRepository ticketTypeRepository;

  private final TicketTypeMapper ticketTypeMapper;

  @Override
  public TicketTypeModel findTicketTypeById(Long ticketTypeId) {
    return ticketTypeMapper.toModel(ticketTypeRepository.findById(ticketTypeId).get());
  }

  @Override
  public TicketTypeModel findTicketTypeByCode(String ticketTypeCode) {
    return ticketTypeMapper.toModel(ticketTypeRepository.findByTypeName(ticketTypeCode));
  }
}
