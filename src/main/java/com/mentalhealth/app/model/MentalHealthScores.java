package com.mentalhealth.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "mental_health_scores")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MentalHealthScores {

    @Id
    @Column(name = "employee_id")
    private Integer employeeId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "employee_id")
    @JsonIgnoreProperties("mentalHealthScores")
    private Employee employee;

    @Column(name = "work_life_balance_score", nullable = false, precision = 4, scale = 2)
    private BigDecimal workLifeBalanceScore;

    @Column(name = "job_satisfaction_score", nullable = false, precision = 4, scale = 2)
    private BigDecimal jobSatisfactionScore;

    @Column(name = "social_support_score", nullable = false, precision = 4, scale = 2)
    private BigDecimal socialSupportScore;

    @Column(name = "stress_score", nullable = false, precision = 4, scale = 2)
    private BigDecimal stressScore;

    @Column(name = "burnout_score", nullable = false, precision = 4, scale = 2)
    private BigDecimal burnoutScore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "burnout_category_id", nullable = false)
    private BurnoutCategory burnoutCategory;

    public MentalHealthScores() {}

    public Integer getEmployeeId() { return employeeId; }
    public void setEmployeeId(Integer employeeId) { this.employeeId = employeeId; }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }

    public BigDecimal getWorkLifeBalanceScore() { return workLifeBalanceScore; }
    public void setWorkLifeBalanceScore(BigDecimal workLifeBalanceScore) { this.workLifeBalanceScore = workLifeBalanceScore; }

    public BigDecimal getJobSatisfactionScore() { return jobSatisfactionScore; }
    public void setJobSatisfactionScore(BigDecimal jobSatisfactionScore) { this.jobSatisfactionScore = jobSatisfactionScore; }

    public BigDecimal getSocialSupportScore() { return socialSupportScore; }
    public void setSocialSupportScore(BigDecimal socialSupportScore) { this.socialSupportScore = socialSupportScore; }

    public BigDecimal getStressScore() { return stressScore; }
    public void setStressScore(BigDecimal stressScore) { this.stressScore = stressScore; }

    public BigDecimal getBurnoutScore() { return burnoutScore; }
    public void setBurnoutScore(BigDecimal burnoutScore) { this.burnoutScore = burnoutScore; }

    public BurnoutCategory getBurnoutCategory() { return burnoutCategory; }
    public void setBurnoutCategory(BurnoutCategory burnoutCategory) { this.burnoutCategory = burnoutCategory; }
}