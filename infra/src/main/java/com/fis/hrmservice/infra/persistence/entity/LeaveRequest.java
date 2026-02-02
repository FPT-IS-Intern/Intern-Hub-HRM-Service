package com.fis.hrmservice.infra.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "leave_requests", schema = "schema_hrm")
public class LeaveRequest {
    @Id
    @Column(name = "ticket_id", nullable = false)
    private long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ticket_id", nullable = false)
    private Ticket tickets;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leave_type_id")
    private LeaveType leaveType;

    @Column(name = "total_days")
    private Integer totalDays;

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