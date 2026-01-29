package com.fis.hrmservice.domain.usecase;

import com.fis.hrmservice.domain.model.constant.CoreConstant;
import com.fis.hrmservice.domain.model.user.Position;
import com.fis.hrmservice.domain.model.user.UserModel;
import com.fis.hrmservice.domain.port.input.RegisterUserUseCase;
import com.fis.hrmservice.domain.port.output.IdGeneratorPort;
import com.fis.hrmservice.domain.port.output.PositionRepositoryPort;
import com.fis.hrmservice.domain.port.output.UserRepositoryPort;
import com.fis.hrmservice.domain.service.UserValidationService;
import com.fis.hrmservice.domain.usecase.command.RegisterUserCommand;
import com.intern.hub.library.common.exception.ConflictDataException;

public class RegisterUserUseCaseImpl implements RegisterUserUseCase {

    private final UserRepositoryPort userRepository;
    private final PositionRepositoryPort positionRepository;
    private final IdGeneratorPort idGenerator;
    private final UserValidationService validationService;

    public RegisterUserUseCaseImpl(
            UserRepositoryPort userRepository,
            PositionRepositoryPort positionRepository,
            IdGeneratorPort idGenerator,
            UserValidationService validationService) {
        this.userRepository = userRepository;
        this.positionRepository = positionRepository;
        this.idGenerator = idGenerator;
        this.validationService = validationService;
    }

    @Override
    public UserModel registerUser(RegisterUserCommand command) {
        // 1. Validate input
        validationService.validateRegistration(command);

        // 2. Check for duplicates
        checkForDuplicates(command);

        // 3. Find position
        Position position = positionRepository.findByCode(command.getPositionCode())
                .orElseThrow(() -> new ConflictDataException("Position not found: " + command.getPositionCode()));

        // 4. Build user model
        UserModel user = buildUserModel(command, position.getPositionId());

        // 5. Save and return
        return userRepository.save(user);
    }

    private void checkForDuplicates(RegisterUserCommand command) {
        if (userRepository.existsByEmail(command.getEmail())) {
            throw new ConflictDataException("Email đã tồn tại");
        }

        if (userRepository.existsByIdNumber(command.getIdNumber())) {
            throw new ConflictDataException("Id user đã bị trùng");
        }
    }

    private UserModel buildUserModel(RegisterUserCommand command, Long positionId) {
        UserModel.UserModelBuilder builder = UserModel.builder()
                .userId(idGenerator.generateId())
                .positionId(positionId)
                .fullName(command.getFullName())
                .idNumber(command.getIdNumber())
                .dateOfBirth(command.getBirthDate())
                .companyEmail(command.getEmail())
                .phoneNumber(command.getPhoneNumber())
                .address(command.getAddress())
                .status(CoreConstant.STATUS_PENDING);

        if (command.isInternRegistration()) {
            builder.internshipStartDate(command.getInternshipStartDate())
                   .internshipEndDate(command.getInternshipEndDate());
        }

        return builder.build();
    }
}
