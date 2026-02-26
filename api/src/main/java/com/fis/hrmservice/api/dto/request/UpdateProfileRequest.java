package com.fis.hrmservice.api.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProfileRequest {
    String fullName;
    String companyEmail;
    LocalDate dateOfBirth;
    String idNumber;
    String address;
    String phoneNumber;
    MultipartFile cvFile;
    MultipartFile avatarFile;
}
