package com.mentalhealth.app.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "work_conditions")
public class WorkConditions {

    @Id
    @Column(name = "employee_id")
    private Integer employeeId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "work_hours_per_week", nullable = false)
    private Integer workHoursPerWeek;

    @Column(name = "meetings_per_day", nullable = false, precision = 4, scale = 1)
    private BigDecimal meetingsPerDay;

    @Column(name = "team_size", nullable = false)
    private Integer teamSize;

    @Column(name = "deadline_pressure_score", nullable = false, precision = 4, scale = 2)
    private BigDecimal deadlinePressureScore;

    @Column(name = "autonomy_score", nullable = false, precision = 4, scale = 2)
    private BigDecimal autonomyScore;

    @Column(name = "manager_support_score", nullable = false, precision = 4, scale = 2)
    private BigDecimal managerSupportScore;

    public WorkConditions() {}

    public Integer getEmployeeId() { return employeeId; }
    public void setEmployeeId(Integer employeeId) { this.employeeId = employeeId; }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }

    public Integer getWorkHoursPerWeek() { return workHoursPerWeek; }
    public void setWorkHoursPerWeek(Integer workHoursPerWeek) { this.workHoursPerWeek = workHoursPerWeek; }

    public BigDecimal getMeetingsPerDay() { return meetingsPerDay; }
    public void setMeetingsPerDay(BigDecimal meetingsPerDay) { this.meetingsPerDay = meetingsPerDay; }

    public Integer getTeamSize() { return teamSize; }
    public void setTeamSize(Integer teamSize) { this.teamSize = teamSize; }

    public BigDecimal getDeadlinePressureScore() { return deadlinePressureScore; }
    public void setDeadlinePressureScore(BigDecimal deadlinePressureScore) { this.deadlinePressureScore = deadlinePressureScore; }

    public BigDecimal getAutonomyScore() { return autonomyScore; }
    public void setAutonomyScore(BigDecimal autonomyScore) { this.autonomyScore = autonomyScore; }

    public BigDecimal getManagerSupportScore() { return managerSupportScore; }
    public void setManagerSupportScore(BigDecimal managerSupportScore) { this.managerSupportScore = managerSupportScore; }
}