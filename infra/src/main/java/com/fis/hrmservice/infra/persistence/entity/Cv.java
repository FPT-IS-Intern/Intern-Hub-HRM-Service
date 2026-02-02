package com.fis.hrmservice.infra.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cv", schema = "schema_hrm")
public class Cv {
    @Id
    @Column(name = "cv_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "cv_url", length = Integer.MAX_VALUE)
    private String cvUrl;

    @Column(name = "created_at")
    private long createdAt;

    @Column(name = "updated_at")
    private long updatedAt;

    @Size(max = 255)
    @Column(name = "created_by")
    private String createdBy;

    @Size(max = 255)
    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "version")
    private Integer version;

    @Size(max = 50)
    @Column(name = "status", length = 50)
    private String status;


}