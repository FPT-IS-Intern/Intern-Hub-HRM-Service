package com.fis.hrmservice.domain.usecase.implement.ticket;

import com.fis.hrmservice.common.utils.DateValidationHelper;
import com.fis.hrmservice.domain.model.constant.RemoteType;
import com.fis.hrmservice.domain.model.constant.TicketStatus;
import com.fis.hrmservice.domain.model.ticket.LeaveRequestModel;
import com.fis.hrmservice.domain.model.ticket.RemoteRequestModel;
import com.fis.hrmservice.domain.model.ticket.TicketModel;
import com.fis.hrmservice.domain.model.user.UserModel;
import com.fis.hrmservice.domain.port.output.ticket.TicketRepositoryPort;
import com.fis.hrmservice.domain.port.output.ticket.TicketTypeRepositoryPort;
import com.fis.hrmservice.domain.port.output.ticket.leaveticket.LeaveRequestRepositoryPort;
import com.fis.hrmservice.domain.port.output.ticket.remoteticket.RemoteRequestRepositoryPort;
import com.fis.hrmservice.domain.port.output.ticket.remoteticket.WorkLocationRepositoryPort;
import com.fis.hrmservice.domain.port.output.user.UserRepositoryPort;
import com.fis.hrmservice.domain.usecase.command.ticket.CreateTicketCommand;
import com.fis.hrmservice.domain.usecase.command.ticket.LeaveRequestCommand;
import com.fis.hrmservice.domain.usecase.command.ticket.RemoteRequestCommand;
import com.intern.hub.library.common.exception.ConflictDataException;
import com.intern.hub.library.common.exception.NotFoundException;
import com.intern.hub.library.common.utils.Snowflake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TicketUseCaseImpl {

    @Autowired
    private TicketRepositoryPort ticketRepositoryPort;

    @Autowired
    private LeaveRequestRepositoryPort leaveRequestRepositoryPort;

    @Autowired
    private TicketTypeRepositoryPort ticketTypeRepositoryPort;

    @Autowired
    private RemoteRequestRepositoryPort remoteRequestRepositoryPort;

    @Autowired
    private WorkLocationRepositoryPort workLocationRepositoryPort;

    @Autowired
    private UserRepositoryPort userRepositoryPort;

    @Autowired
    private Snowflake snowflake;

    /* ================= BASE TICKET ================= */

    private TicketModel createBaseTicket(CreateTicketCommand command, Long userId) {

        UserModel requester =
                userRepositoryPort.findById(userId)
                        .orElseThrow(() -> new NotFoundException("User not found: " + userId));

        DateValidationHelper.validateDate(command.getFromDate(), command.getToDate());

        TicketModel ticket =
                TicketModel.builder()
                        .ticketId(snowflake.next())
                        .requester(requester)
                        .ticketType(
                                ticketTypeRepositoryPort
                                        .findTicketTypeByCode(command.getTicketType())
                        )
                        .startAt(command.getFromDate())
                        .endAt(command.getToDate())
                        .reason(command.getReason())
                        .sysStatus(TicketStatus.PENDING)
                        .build();

        return ticketRepositoryPort.save(ticket);
    }

    /* ================= LEAVE ================= */

    public LeaveRequestModel createLeaveRequest(
            CreateTicketCommand ticketCommand,
            LeaveRequestCommand leaveCommand,
            Long userId
    ) {

        if (leaveCommand.getTotalDays() <= 0) {
            throw new ConflictDataException("Total days must be greater than 0");
        }

        TicketModel ticket = createBaseTicket(ticketCommand, userId);

        ticket = ticketRepositoryPort.save(ticket);

        LeaveRequestModel model =
                LeaveRequestModel.builder()
                        .ticket(ticket)
                        .totalDays(leaveCommand.getTotalDays())
                        .build();

        return leaveRequestRepositoryPort.save(model);
    }

    /* ================= REMOTE ================= */

    public RemoteRequestModel createRemoteRequest(
            CreateTicketCommand ticketCommand,
            RemoteRequestCommand remoteCommand,
            Long userId
    ) {

        RemoteType remoteType;

        try {
            remoteType = RemoteType.valueOf(ticketCommand.getTicketType().toUpperCase());
        } catch (Exception e) {
            throw new ConflictDataException("Invalid remote type");
        }

        TicketModel ticket = createBaseTicket(ticketCommand, userId);

        return switch (remoteType) {

            case WFH -> remoteRequestRepositoryPort.save(
                    RemoteRequestModel.builder()
                            .ticket(ticket)
                            .remoteType(RemoteType.WFH)
                            .build()
            );

            case ONSITE -> {

                DateValidationHelper.validateHour(
                        remoteCommand.getStartTime(),
                        remoteCommand.getEndTime()
                );

                if (!workLocationRepositoryPort.existByLocationName(remoteCommand.getLocation())) {
                    throw new ConflictDataException("Location not found");
                }

                yield remoteRequestRepositoryPort.save(
                        RemoteRequestModel.builder()
                                .ticket(ticket)
                                .remoteType(RemoteType.ONSITE)
                                .workLocation(
                                        workLocationRepositoryPort
                                                .findByLocationName(remoteCommand.getLocation())
                                )
                                .startTime(remoteCommand.getStartTime())
                                .endTime(remoteCommand.getEndTime())
                                .build()
                );
            }
        };
    }

    /* ================= EXPLANATION ================= */

    public TicketModel createExplanationTicket(CreateTicketCommand command, Long userId) {

        TicketModel ticket = createBaseTicket(command, userId);

        ticket.setEndAt(null);

        // KHÔNG save lại ticket lần 2
        return ticket;
    }
}
