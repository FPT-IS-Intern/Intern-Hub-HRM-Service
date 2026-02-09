package com.fis.hrmservice.infra.persistence.entity;

import com.fis.hrmservice.domain.model.constant.UserStatus;
import com.intern.hub.starter.security.entity.AuditEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends AuditEntity {
  @Id
  @Column(name = "user_id", nullable = false)
  private Long id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "position_id")
  private Position position;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "mentor_id")
  private User mentor;

  @Size(max = 100)
  @Column(name = "full_name", length = 100)
  private String fullName;

  @Size(max = 12)
  @Column(name = "id_number", length = 12)
  private String idNumber;

  @Column(name = "date_of_birth")
  private LocalDate dateOfBirth;

  @Size(max = 100)
  @Column(name = "company_email", length = 100)
  private String companyEmail;

  @Size(max = 20)
  @Column(name = "phone_number", length = 20)
  private String phoneNumber;

  @Column(name = "internship_start_date")
  private LocalDate internshipStartDate;

  @Column(name = "internship_end_date")
  private LocalDate internshipEndDate;

  @NotNull
  @Column(name = "address", nullable = false, length = Integer.MAX_VALUE)
  private String address;

  @Enumerated(EnumType.STRING)
  @Column(name = "sys_status", length = 20)
  private UserStatus sysStatus;
}
