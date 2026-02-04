package com.fis.hrmservice.infra.mapper;

import com.fis.hrmservice.domain.model.ticket.TicketModel;
import com.fis.hrmservice.infra.persistence.entity.Ticket;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        uses = {
                UserMapper.class,
                TicketTypeMapper.class
        },
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface TicketMapper {

    @Mapping(source = "ticketId", target = "id")
    @Mapping(target = "user", expression = "java(User.builder().id(model.getRequester().getUserId()).build())")
    Ticket toEntity(TicketModel model);

    @InheritInverseConfiguration
    TicketModel toModel(Ticket entity);
}