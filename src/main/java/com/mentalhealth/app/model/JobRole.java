package com.mentalhealth.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "job_role")
public class JobRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_role_id")
    private Integer jobRoleId;

    @Column(name = "role_name", nullable = false, unique = true, length = 100)
    private String roleName;

    public JobRole() {}

    public Integer getJobRoleId() { return jobRoleId; }
    public void setJobRoleId(Integer jobRoleId) { this.jobRoleId = jobRoleId; }

    public String getRoleName() { return roleName; }
    public void setRoleName(String roleName) { this.roleName = roleName; }
}