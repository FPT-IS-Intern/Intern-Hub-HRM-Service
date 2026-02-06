package com.fis.hrmservice.api.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InternalUserProfile {
    Long userId;
    String idNumber;
    String companyEmail;
}
