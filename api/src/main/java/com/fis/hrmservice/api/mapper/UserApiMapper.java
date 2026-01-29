package com.fis.hrmservice.api.mapper;

import com.fis.hrmservice.api.dto.request.RegisterUserRequest;
import com.fis.hrmservice.domain.usecase.command.RegisterUserCommand;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * Mapper to convert API DTOs to Domain Commands.
 * This is where Spring-specific types (like MultipartFile) are converted to plain data.
 */
@Component
public class UserApiMapper {

    /**
     * Convert RegisterUserRequest (API DTO with MultipartFile) to RegisterUserCommand (Domain Command)
     */
    public RegisterUserCommand toCommand(RegisterUserRequest request) {
        MultipartFile cv = request.getCv();
        MultipartFile avatar = request.getAvatar();

        return RegisterUserCommand.builder()
                .email(request.getEmail())
                .fullName(request.getFullName())
                .idNumber(request.getIdNumber())
                .birthDate(request.getBirthDate())
                .address(request.getAddress())
                .phoneNumber(request.getPhoneNumber())
                .positionCode(request.getPositionCode())
                .internshipStartDate(request.getInternshipStartDate())
                .internshipEndDate(request.getInternshipEndDate())
                // Extract file metadata (not the actual file stream)
                .cvFileName(cv != null ? cv.getOriginalFilename() : null)
                .cvContentType(cv != null ? cv.getContentType() : null)
                .cvSize(cv != null ? cv.getSize() : 0)
                .avatarFileName(avatar != null ? avatar.getOriginalFilename() : null)
                .avatarContentType(avatar != null ? avatar.getContentType() : null)
                .avatarSize(avatar != null ? avatar.getSize() : 0)
                .build();
    }
}
