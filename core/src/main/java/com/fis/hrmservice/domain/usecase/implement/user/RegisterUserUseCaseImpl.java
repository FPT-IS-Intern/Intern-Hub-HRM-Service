package com.fis.hrmservice.domain.usecase.implement.user;

import com.fis.hrmservice.domain.model.constant.TicketStatus;
import com.fis.hrmservice.domain.model.constant.TicketType;
import com.fis.hrmservice.domain.model.constant.UserStatus;
import com.fis.hrmservice.domain.model.ticket.TicketModel;
import com.fis.hrmservice.domain.model.user.AvatarModel;
import com.fis.hrmservice.domain.model.user.CvModel;
import com.fis.hrmservice.domain.model.user.PositionModel;
import com.fis.hrmservice.domain.model.user.UserModel;
import com.fis.hrmservice.domain.port.output.ticket.TicketRepositoryPort;
import com.fis.hrmservice.domain.port.output.ticket.TicketTypeRepositoryPort;
import com.fis.hrmservice.domain.port.output.user.*;
import com.fis.hrmservice.domain.service.UserValidationService;
import com.fis.hrmservice.domain.usecase.command.user.RegisterUserCommand;
import com.intern.hub.library.common.exception.ConflictDataException;
import com.intern.hub.library.common.utils.Snowflake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
public class RegisterUserUseCaseImpl {

    @Autowired
    private UserRepositoryPort userRepositoryPort;
    @Autowired
    private PositionRepositoryPort positionRepositoryPort;
    @Autowired
    private Snowflake snowflake;
    @Autowired
    private UserValidationService validationService;
    @Autowired
    private TicketRepositoryPort ticketRepositoryPort;
    @Autowired
    private TicketTypeRepositoryPort ticketTypeRepositoryPort;
    @Autowired
    private FileStoragePort fileStoragePort;
    @Autowired
    private AvatarRepositoryPort avatarRepositoryPort;

    @Autowired
    private CvRepositoryPort cvRepositoryPort;

    public UserModel registerUser(RegisterUserCommand command) {

        // 1️⃣ Validate business rule
        validationService.validateRegistration(command);
        checkForDuplicates(command);
        validateFiles(command);

        // 2️⃣ Get position
        PositionModel position =
                positionRepositoryPort
                        .findByCode(command.getPositionCode())
                        .orElseThrow(() -> new ConflictDataException("Position không tồn tại"));

        // 3️⃣ Build user
        UserModel user = buildUserModel(command, position);

        // 4️⃣ Save user
        UserModel savedUser = userRepositoryPort.save(user);
        if (savedUser == null) {
            throw new ConflictDataException("Cannot save user");
        }

        try {

            // 5️⃣ Upload Avatar
            String avatarUrl =
                    fileStoragePort.upload(
                            command.getAvatar().getBytes(),
                            command.getAvatar().getOriginalFilename(),
                            command.getAvatar().getContentType(),
                            "avatars/" + savedUser.getUserId());

            AvatarModel avatar =
                    avatarRepositoryPort.save(
                            AvatarModel.builder()
                                    .avatarId(snowflake.next())
                                    .user(savedUser)
                                    .avatarUrl(avatarUrl)
                                    .fileName(command.getAvatar().getOriginalFilename())
                                    .fileSize(command.getAvatar().getSize())
                                    .fileType(command.getAvatar().getContentType())
                                    .build());

            if (avatar == null) {
                throw new ConflictDataException("Cannot save avatar");
            }

            // 6️⃣ Upload CV
            String cvUrl =
                    fileStoragePort.upload(
                            command.getCv().getBytes(),
                            command.getCv().getOriginalFilename(),
                            command.getCv().getContentType(),
                            "cvs/" + savedUser.getUserId());

            CvModel cv =
                    cvRepositoryPort.save(
                            CvModel.builder()
                                    .cvId(snowflake.next())
                                    .user(savedUser)
                                    .cvUrl(cvUrl)
                                    .fileName(command.getCv().getOriginalFilename())
                                    .fileSize(command.getCv().getSize())
                                    .fileType(command.getCv().getContentType())
                                    .build());

            if (cv == null) {
                throw new ConflictDataException("Cannot save CV");
            }

        } catch (Exception e) {
            log.error("Register process failed. Transaction rollback triggered.", e);
            throw new ConflictDataException("Register failed");
        }

        // 7️⃣ Create registration ticket
        ticketRepositoryPort.save(
                TicketModel.builder()
                        .ticketId(snowflake.next())
                        .requester(savedUser)
                        .ticketType(
                                ticketTypeRepositoryPort.findTicketTypeByCode(
                                        String.valueOf(TicketType.REGISTRATION)))
                        .startAt(LocalDate.now())
                        .endAt(null)
                        .reason("Đăng ký tài khoản")
                        .sysStatus(TicketStatus.PENDING)
                        .build());

        return savedUser;
    }

    private void validateFiles(RegisterUserCommand command) {

        if (command.getAvatar() == null || command.getAvatar().isEmpty()) {
            throw new ConflictDataException("Avatar is required");
        }

        if (command.getCv() == null || command.getCv().isEmpty()) {
            throw new ConflictDataException("CV is required");
        }

        String avatarType = command.getAvatar().getContentType();
        if (avatarType == null || !avatarType.matches("image/(png|jpeg|webp)")) {
            throw new ConflictDataException("Unsupported avatar type");
        }

        String cvType = command.getCv().getContentType();
        if (cvType == null || !cvType.equals("application/pdf")) {
            throw new ConflictDataException("CV must be PDF");
        }
    }

    private void checkForDuplicates(RegisterUserCommand command) {

        if (userRepositoryPort.existsByEmail(command.getEmail())) {
            throw new ConflictDataException("Email đã tồn tại");
        }

        if (userRepositoryPort.existsByIdNumber(command.getIdNumber())) {
            throw new ConflictDataException("Id user đã bị trùng");
        }
    }

    private UserModel buildUserModel(RegisterUserCommand command, PositionModel position) {

        UserModel.UserModelBuilder builder =
                UserModel.builder()
                        .userId(snowflake.next())
                        .position(position)
                        .fullName(command.getFullName())
                        .idNumber(command.getIdNumber())
                        .dateOfBirth(command.getBirthDate())
                        .companyEmail(command.getEmail())
                        .phoneNumber(command.getPhoneNumber())
                        .address(command.getAddress())
                        .sysStatus(UserStatus.PENDING);

        if (command.isInternRegistration()) {
            builder
                    .internshipStartDate(command.getInternshipStartDate())
                    .internshipEndDate(command.getInternshipEndDate());
        }

        return builder.build();
    }
}
