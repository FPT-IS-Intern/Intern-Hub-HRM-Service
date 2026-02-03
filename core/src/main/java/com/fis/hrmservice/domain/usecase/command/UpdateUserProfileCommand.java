package com.fis.hrmservice.domain.usecase.command;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateUserProfileCommand {
    String fullName;
    String companyEmail;
    LocalDate dateOfBirth;
    String idNumber;
    String address;
    String phoneNumber;
    MultipartFile cvFile;
    MultipartFile avatarFile;
}
