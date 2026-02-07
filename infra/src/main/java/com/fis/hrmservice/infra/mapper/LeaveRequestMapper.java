package com.fis.hrmservice.infra.mapper;

import com.fis.hrmservice.domain.model.ticket.LeaveRequestModel;
import com.fis.hrmservice.infra.persistence.entity.LeaveRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(
        componentModel = "spring",
        uses = {
                TicketMapper.class
        }
)
public interface LeaveRequestMapper {

  /* ================= ENTITY → MODEL ================= */

  @Mapping(source = "tickets", target = "ticket")
  LeaveRequestModel toModel(LeaveRequest entity);


  /* ================= MODEL → ENTITY ================= */

  @Mapping(source = "ticket", target = "tickets")

  // IMPORTANT: KHÔNG set id thủ công — @MapsId sẽ tự copy từ Ticket
  @Mapping(target = "id", ignore = true)

  // audit + system fields
  @Mapping(target = "leaveTypeId", ignore = true)
  @Mapping(target = "status", ignore = true)
  @Mapping(target = "version", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "createdBy", ignore = true)
  @Mapping(target = "updatedBy", ignore = true)

  LeaveRequest toEntity(LeaveRequestModel model);
}
