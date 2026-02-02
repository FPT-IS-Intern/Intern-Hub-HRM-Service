package com.fis.hrmservice.api.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProfileResponse {
    private String fullName;
    private String idNumber;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String companyEmail;
    private String address;
    private String position;
    private LocalDate internshipStartDate;
    private LocalDate internshipEndDate;
    private String avatarUrl;
    private String cvUrl;
    private String role;
    private String superVisorName;
    private int scoreAward;
    private int budgetPoints;
    private String sysStatus;
}
