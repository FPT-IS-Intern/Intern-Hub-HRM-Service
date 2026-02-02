package com.fis.hrmservice.infra.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "work_locations", schema = "schema_hrm")
public class WorkLocation {
    @Id
    @Column(name = "work_location_id", nullable = false)
    private Long id;

    @Size(max = 255)
    @Column(name = "name")
    private String name;

    @Size(max = 255)
    @Column(name = "address")
    private String address;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "created_at")
    private long createdAt;

    @Column(name = "updated_at")
    private long updatedAt;

    @Column(name = "version")
    private Integer version;

    @Size(max = 255)
    @Column(name = "created_by")
    private String createdBy;

    @Size(max = 255)
    @Column(name = "updated_by")
    private String updatedBy;

    @Size(max = 50)
    @Column(name = "status", length = 50)
    private String status;


}