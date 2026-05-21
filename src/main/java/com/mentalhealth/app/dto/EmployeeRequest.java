package com.mentalhealth.app.dto;

import lombok.Data;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Getter
@Setter
public class EmployeeRequest {

    private Integer employeeId;

    @NotNull @Min(18) @Max(80)
    private Integer age;

    @NotBlank
    private String gender;

    @NotNull
    private Integer countryId;

    @NotNull
    private Integer jobRoleId;

    @NotNull
    private Integer seniorityLevelId;

    @NotNull
    private Integer companySizeId;

    @NotNull
    private Integer industryId;

    @NotNull
    private Integer workModeId;

    @NotNull @DecimalMin("0.0")
    private BigDecimal yearsExperience;

    @NotNull @DecimalMin("0.0")
    private BigDecimal yearsAtCompany;

    @NotNull @DecimalMin("0.0")
    private BigDecimal salaryUsd;

    // Work conditions
    @NotNull @Min(0)
    private Integer workHoursPerWeek;

    @NotNull @DecimalMin("0.0")
    private BigDecimal meetingsPerDay;

    @NotNull @Min(1)
    private Integer teamSize;

    @NotNull @DecimalMin("0.0") @DecimalMax("10.0")
    private BigDecimal deadlinePressureScore;

    @NotNull @DecimalMin("0.0") @DecimalMax("10.0")
    private BigDecimal autonomyScore;

    @NotNull @DecimalMin("0.0") @DecimalMax("10.0")
    private BigDecimal managerSupportScore;

    // Lifestyle
    @NotNull @DecimalMin("0.0") @DecimalMax("24.0")
    private BigDecimal sleepHoursPerNight;

    @NotNull @Min(0) @Max(7)
    private Integer exerciseDaysPerWeek;

    @NotNull @Min(0)
    private Integer vacationDaysTaken;

    // Mental health scores
    @NotNull @DecimalMin("0.0") @DecimalMax("10.0")
    private BigDecimal workLifeBalanceScore;

    @NotNull @DecimalMin("0.0") @DecimalMax("10.0")
    private BigDecimal jobSatisfactionScore;

    @NotNull @DecimalMin("0.0") @DecimalMax("10.0")
    private BigDecimal socialSupportScore;

    @NotNull @DecimalMin("0.0") @DecimalMax("10.0")
    private BigDecimal stressScore;

    @NotNull @DecimalMin("0.0") @DecimalMax("10.0")
    private BigDecimal burnoutScore;

    @NotNull
    private Integer burnoutCategoryId;

    // Clinical scores
    @NotNull @Min(0) @Max(27)
    private Integer phq9Score;

    @NotNull
    private Integer phq9CategoryId;

    @NotNull @Min(0) @Max(21)
    private Integer gad7Score;

    @NotNull
    private Integer gad7CategoryId;

    // Therapy & AI
    @NotNull
    private Boolean therapyAccess;

    @NotNull
    private Boolean usesTherapy;

    @NotNull
    private Boolean aiToolsDaily;

    // Outcomes
    @NotNull
    private Boolean seeksMentalHealthSupport;

    @NotNull
    private Boolean jobChangeIntention;
}