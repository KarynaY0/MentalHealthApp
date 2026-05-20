package com.mentalhealth.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "therapy_info")
public class TherapyInfo {

    @Id
    @Column(name = "employee_id")
    private Integer employeeId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "therapy_access", nullable = false)
    private Boolean therapyAccess = false;

    @Column(name = "uses_therapy", nullable = false)
    private Boolean usesTherapy = false;

    public TherapyInfo() {}

    public Integer getEmployeeId() { return employeeId; }
    public void setEmployeeId(Integer employeeId) { this.employeeId = employeeId; }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }

    public Boolean getTherapyAccess() { return therapyAccess; }
    public void setTherapyAccess(Boolean therapyAccess) { this.therapyAccess = therapyAccess; }

    public Boolean getUsesTherapy() { return usesTherapy; }
    public void setUsesTherapy(Boolean usesTherapy) { this.usesTherapy = usesTherapy; }
}