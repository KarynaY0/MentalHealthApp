package com.mentalhealth.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "employee")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Employee {

    @Id
    @Column(name = "employee_id")
    private Integer employeeId;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "gender", nullable = false, length = 50)
    private String gender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_role_id", nullable = false)
    private JobRole jobRole;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seniority_level_id", nullable = false)
    private SeniorityLevel seniorityLevel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_size_id", nullable = false)
    private CompanySize companySize;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "industry_id", nullable = false)
    private Industry industry;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_mode_id", nullable = false)
    private WorkMode workMode;

    @Column(name = "years_experience", nullable = false, precision = 5, scale = 1)
    private BigDecimal yearsExperience;

    @Column(name = "years_at_company", nullable = false, precision = 5, scale = 1)
    private BigDecimal yearsAtCompany;

    @Column(name = "salary_usd", nullable = false, precision = 10, scale = 2)
    private BigDecimal salaryUsd;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("employee")
    private WorkConditions workConditions;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("employee")
    private LifestyleMetrics lifestyleMetrics;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("employee")
    private MentalHealthScores mentalHealthScores;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("employee")
    private ClinicalScores clinicalScores;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("employee")
    private TherapyInfo therapyInfo;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("employee")
    private AiToolUsage aiToolUsage;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("employee")
    private EmployeeOutcomes employeeOutcomes;


    public Employee() {}

    public Integer getEmployeeId() { return employeeId; }
    public void setEmployeeId(Integer employeeId) { this.employeeId = employeeId; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public Country getCountry() { return country; }
    public void setCountry(Country country) { this.country = country; }

    public JobRole getJobRole() { return jobRole; }
    public void setJobRole(JobRole jobRole) { this.jobRole = jobRole; }

    public SeniorityLevel getSeniorityLevel() { return seniorityLevel; }
    public void setSeniorityLevel(SeniorityLevel seniorityLevel) { this.seniorityLevel = seniorityLevel; }

    public CompanySize getCompanySize() { return companySize; }
    public void setCompanySize(CompanySize companySize) { this.companySize = companySize; }

    public Industry getIndustry() { return industry; }
    public void setIndustry(Industry industry) { this.industry = industry; }

    public WorkMode getWorkMode() { return workMode; }
    public void setWorkMode(WorkMode workMode) { this.workMode = workMode; }

    public BigDecimal getYearsExperience() { return yearsExperience; }
    public void setYearsExperience(BigDecimal yearsExperience) { this.yearsExperience = yearsExperience; }

    public BigDecimal getYearsAtCompany() { return yearsAtCompany; }
    public void setYearsAtCompany(BigDecimal yearsAtCompany) { this.yearsAtCompany = yearsAtCompany; }

    public BigDecimal getSalaryUsd() { return salaryUsd; }
    public void setSalaryUsd(BigDecimal salaryUsd) { this.salaryUsd = salaryUsd; }

    public WorkConditions getWorkConditions(){return workConditions;}
    public void setWorkConditions(WorkConditions workConditions){this.workConditions = workConditions;}

    public LifestyleMetrics getLifestyleMetrics(){return lifestyleMetrics;}
    public void setLifestyleMetrics(LifestyleMetrics lifestyleMetrics){this.lifestyleMetrics = lifestyleMetrics;}

    public MentalHealthScores getMentalHealthScores(){return mentalHealthScores;}
    public void setMentalHealthScores(MentalHealthScores mentalHealthScores){this.mentalHealthScores = mentalHealthScores;}

    public ClinicalScores getClinicalScores(){return clinicalScores;}
    public void setClinicalScores(ClinicalScores clinicalScores){this.clinicalScores = clinicalScores;}

    public TherapyInfo getTherapyInfo(){return therapyInfo;}
    public void setTherapyInfo(TherapyInfo therapyInfo){this.therapyInfo = therapyInfo;}

    public AiToolUsage getAiToolUsage(){return aiToolUsage;}
    public void setAiToolUsage(AiToolUsage aiToolUsage){this.aiToolUsage = aiToolUsage;}

    public EmployeeOutcomes getEmployeeOutcomes(){return employeeOutcomes;}
    public void setEmployeeOutcomes(EmployeeOutcomes employeeOutcomes){this.employeeOutcomes = employeeOutcomes;}
}