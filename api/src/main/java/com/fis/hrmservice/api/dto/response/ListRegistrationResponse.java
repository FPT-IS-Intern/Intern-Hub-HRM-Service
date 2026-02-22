package com.fis.hrmservice.api.dto.response;

import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class ListRegistrationResponse {
    int no;
    Long ticketId;
    String fullName;
    String companyEmail;
    String departmentName;
    String ticketTypeName;
    String ticketStatus;
}
