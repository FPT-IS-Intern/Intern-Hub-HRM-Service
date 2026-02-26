package com.fis.hrmservice.domain.usecase.command.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterUserCommand {

    String email;
    String fullName;
    String idNumber;
    LocalDate birthDate;
    String address;
    String phoneNumber;
    String positionCode;

    LocalDate internshipStartDate;
    LocalDate internshipEndDate;

    MultipartFile avatar;
    MultipartFile cv;

    public boolean isInternRegistration() {
        return positionCode != null && positionCode.toUpperCase().contains("INTERN");
    }
}
