package com.fis.hrmservice.infra.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

/**
 * JPA Entity for users table.
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity {

    @Id
    @Column(name = "user_id")
    Long userId;

    @Column(name = "position_id")
    Long positionId;

    @Column(name = "mentor_id")
    Long mentorId;

    @Column(name = "full_name", length = 100)
    String fullName;

    @Column(name = "id_number", length = 12)
    String idNumber;

    @Column(name = "date_of_birth")
    LocalDate dateOfBirth;

    @Column(name = "company_email", length = 100)
    String companyEmail;

    @Column(name = "phone_number", length = 20)
    String phoneNumber;

    @Column(name = "address", nullable = false, columnDefinition = "TEXT")
    String address;

    @Column(name = "internship_start_date")
    LocalDate internshipStartDate;

    @Column(name = "internship_end_date")
    LocalDate internshipEndDate;

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
