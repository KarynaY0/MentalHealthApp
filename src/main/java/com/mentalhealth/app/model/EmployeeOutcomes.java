package com.mentalhealth.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "employee_outcomes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class EmployeeOutcomes {

    @Id
    @Column(name = "employee_id")
    private Integer employeeId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "employee_id")
    @JsonIgnoreProperties("employeeOutcomes")
    private Employee employee;

    @Column(name = "seeks_mental_health_support", nullable = false)
    private Boolean seeksMentalHealthSupport = false;

    @Column(name = "job_change_intention", nullable = false)
    private Boolean jobChangeIntention = false;

    public EmployeeOutcomes() {}

    public Integer getEmployeeId() { return employeeId; }
    public void setEmployeeId(Integer employeeId) { this.employeeId = employeeId; }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }

    public Boolean getSeeksMentalHealthSupport() { return seeksMentalHealthSupport; }
    public void setSeeksMentalHealthSupport(Boolean seeksMentalHealthSupport) { this.seeksMentalHealthSupport = seeksMentalHealthSupport; }

    public Boolean getJobChangeIntention() { return jobChangeIntention; }
    public void setJobChangeIntention(Boolean jobChangeIntention) { this.jobChangeIntention = jobChangeIntention; }
}