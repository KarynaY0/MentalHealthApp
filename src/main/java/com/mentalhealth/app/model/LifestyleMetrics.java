package com.mentalhealth.app.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "lifestyle_metrics")
public class LifestyleMetrics {

    @Id
    @Column(name = "employee_id")
    private Integer employeeId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "sleep_hours_per_night", nullable = false, precision = 4, scale = 1)
    private BigDecimal sleepHoursPerNight;

    @Column(name = "exercise_days_per_week", nullable = false)
    private Integer exerciseDaysPerWeek;

    @Column(name = "vacation_days_taken", nullable = false)
    private Integer vacationDaysTaken;

    public LifestyleMetrics() {}

    public Integer getEmployeeId() { return employeeId; }
    public void setEmployeeId(Integer employeeId) { this.employeeId = employeeId; }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }

    public BigDecimal getSleepHoursPerNight() { return sleepHoursPerNight; }
    public void setSleepHoursPerNight(BigDecimal sleepHoursPerNight) { this.sleepHoursPerNight = sleepHoursPerNight; }

    public Integer getExerciseDaysPerWeek() { return exerciseDaysPerWeek; }
    public void setExerciseDaysPerWeek(Integer exerciseDaysPerWeek) { this.exerciseDaysPerWeek = exerciseDaysPerWeek; }

    public Integer getVacationDaysTaken() { return vacationDaysTaken; }
    public void setVacationDaysTaken(Integer vacationDaysTaken) { this.vacationDaysTaken = vacationDaysTaken; }
}