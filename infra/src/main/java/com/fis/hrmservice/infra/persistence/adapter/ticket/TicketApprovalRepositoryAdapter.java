package com.fis.hrmservice.infra.persistence.adapter.ticket;

import com.fis.hrmservice.domain.model.ticket.TicketApprovalModel;
import com.fis.hrmservice.domain.port.output.ticket.TicketApprovalRepositoryPort;
import com.fis.hrmservice.infra.mapper.TicketApprovalMapper;
import com.fis.hrmservice.infra.persistence.entity.TicketApproval;
import com.fis.hrmservice.infra.persistence.repository.ticket.TicketApprovalJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TicketApprovalRepositoryAdapter implements TicketApprovalRepositoryPort {

  private final TicketApprovalJpaRepository ticketApprovalJpaRepository;

  private final TicketApprovalMapper ticketApprovalMapper;

  @Override
  public void save(TicketApprovalModel approvalModel) {
    TicketApproval entity = ticketApprovalMapper.toEntity(approvalModel);
    ticketApprovalJpaRepository.save(entity);
  }
}
