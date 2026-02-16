package com.fis.hrmservice.api.dto.request;

import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class FilterRegistrationRequest {
    String keyword;
    String ticketStatus;
}