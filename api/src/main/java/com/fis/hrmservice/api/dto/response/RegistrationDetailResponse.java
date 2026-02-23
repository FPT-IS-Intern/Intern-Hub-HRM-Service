package com.fis.hrmservice.api.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegistrationDetailResponse {
    String avatarUrl;
    String positionName;
    String cvUrl;
    LocalDate internshipStartDate;
    LocalDate internshipEndDate;
    String fullName;
    String idNumber;
    LocalDate dateOfBirth;
    String phoneNumber;
    String companyEmail;
    String address;
    String sysStatus;
}
