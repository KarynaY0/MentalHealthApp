package com.mentalhealth.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "ai_tool_usage")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AiToolUsage {

    @Id
    @Column(name = "employee_id")
    private Integer employeeId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "employee_id")
    @JsonIgnoreProperties("aiToolUsage")
    private Employee employee;

    @Column(name = "ai_tools_daily", nullable = false)
    private Boolean aiToolsDaily = false;

    public AiToolUsage() {}

    public Integer getEmployeeId() { return employeeId; }
    public void setEmployeeId(Integer employeeId) { this.employeeId = employeeId; }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }

    public Boolean getAiToolsDaily() { return aiToolsDaily; }
    public void setAiToolsDaily(Boolean aiToolsDaily) { this.aiToolsDaily = aiToolsDaily; }
}