package com.fis.hrmservice.infra.mapper;

import com.fis.hrmservice.domain.model.ticket.TicketTypeModel;
import com.fis.hrmservice.infra.persistence.entity.TicketType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TicketTypeMapper {

    @Mapping(source = "id", target = "ticketTypeId")
    TicketTypeModel toModel(TicketType entity);

    @Mapping(source = "ticketTypeId", target = "id")
    TicketType toEntity(TicketTypeModel model);
}
