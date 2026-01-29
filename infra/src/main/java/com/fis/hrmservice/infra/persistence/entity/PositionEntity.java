package com.fis.hrmservice.infra.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * JPA Entity for positions table.
 */
@Entity
@Table(name = "positions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PositionEntity {

    @Id
    @Column(name = "position_id")
    Long positionId;

    @Column(name = "name", length = 10)
    String name;

    @Column(name = "description", columnDefinition = "TEXT")
    String description;

    @Column(name = "status", length = 50)
    String status;

    @Version
    @Column(name = "version")
    Integer version;

    @Column(name = "created_at")
    Long createdAt;

    @Column(name = "updated_at")
    Long updatedAt;

    @Column(name = "created_by")
    String createdBy;

    @Column(name = "updated_by")
    String updatedBy;
}
