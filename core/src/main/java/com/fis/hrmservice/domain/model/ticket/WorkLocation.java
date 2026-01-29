package com.fis.hrmservice.domain.model.ticket;

import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * Domain model for WorkLocation entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WorkLocation {
    Long workLocationId;
    String name;
    String address;
    String description;
    Boolean isActive;
    String status;
}