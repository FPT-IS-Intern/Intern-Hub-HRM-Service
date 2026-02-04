package com.fis.hrmservice.infra.mapper;

import com.fis.hrmservice.domain.model.constant.TicketType;
import com.fis.hrmservice.domain.model.ticket.TicketTypeModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TicketTypeMapper {
    TicketType toEntity(TicketTypeModel model);
    TicketTypeModel toModel(TicketType entity);
}