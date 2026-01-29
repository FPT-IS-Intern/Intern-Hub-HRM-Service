package com.fis.hrmservice.api.dto.response;

import com.fis.hrmservice.domain.model.user.UserModel;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

/**
 * Response DTO for user data.
 */
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {

    Long userId;
    String fullName;
    String email;
    String phoneNumber;
    String address;
    LocalDate dateOfBirth;
    String positionCode;
    LocalDate internshipStartDate;
    LocalDate internshipEndDate;
    String status;

    public static UserResponse fromModel(UserModel model) {
        return UserResponse.builder()
                .userId(model.getUserId())
                .fullName(model.getFullName())
                .email(model.getCompanyEmail())
                .phoneNumber(model.getPhoneNumber())
                .address(model.getAddress())
                .dateOfBirth(model.getDateOfBirth())
                .internshipStartDate(model.getInternshipStartDate())
                .internshipEndDate(model.getInternshipEndDate())
                .status(model.getStatus())
                .build();
    }
}
