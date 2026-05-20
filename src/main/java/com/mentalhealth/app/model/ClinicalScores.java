package com.mentalhealth.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "clinical_scores")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ClinicalScores {

    @Id
    @Column(name = "employee_id")
    private Integer employeeId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "employee_id")
    @JsonIgnoreProperties("clinicalScores")
    private Employee employee;

    @Column(name = "phq9_score", nullable = false)
    private Integer phq9Score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "phq9_category_id", nullable = false)
    private Phq9Category phq9Category;

    @Column(name = "gad7_score", nullable = false)
    private Integer gad7Score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gad7_category_id", nullable = false)
    private Gad7Category gad7Category;

    public ClinicalScores() {}

    public Integer getEmployeeId() { return employeeId; }
    public void setEmployeeId(Integer employeeId) { this.employeeId = employeeId; }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }

    public Integer getPhq9Score() { return phq9Score; }
    public void setPhq9Score(Integer phq9Score) { this.phq9Score = phq9Score; }

    public Phq9Category getPhq9Category() { return phq9Category; }
    public void setPhq9Category(Phq9Category phq9Category) { this.phq9Category = phq9Category; }

    public Integer getGad7Score() { return gad7Score; }
    public void setGad7Score(Integer gad7Score) { this.gad7Score = gad7Score; }

    public Gad7Category getGad7Category() { return gad7Category; }
    public void setGad7Category(Gad7Category gad7Category) { this.gad7Category = gad7Category; }
}