package com.mentalhealth.app.repository;

import com.mentalhealth.app.model.WorkConditions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface WorkConditionsRepository extends JpaRepository<WorkConditions, Integer> {

    List<WorkConditions> findByWorkHoursPerWeekGreaterThan(Integer hours);

    List<WorkConditions> findByDeadlinePressureScoreGreaterThan(BigDecimal score);

    List<WorkConditions> findByAutonomyScoreLessThan(BigDecimal score);

    List<WorkConditions> findByManagerSupportScoreLessThan(BigDecimal score);

    @Query("SELECT r.roleName, AVG(w.workHoursPerWeek) FROM WorkConditions w " +
            "JOIN w.employee e JOIN e.jobRole r GROUP BY r.roleName ORDER BY AVG(w.workHoursPerWeek) DESC")
    List<Object[]> findAvgWorkHoursGroupedByJobRole();

    @Query("SELECT i.industryName, AVG(w.deadlinePressureScore) FROM WorkConditions w " +
            "JOIN w.employee e JOIN e.industry i GROUP BY i.industryName ORDER BY AVG(w.deadlinePressureScore) DESC")
    List<Object[]> findAvgDeadlinePressureGroupedByIndustry();

    @Query("SELECT AVG(w.workHoursPerWeek) FROM WorkConditions w " +
            "JOIN w.employee e JOIN e.industry i WHERE i.industryName = :industryName")
    BigDecimal findAvgWorkHoursByIndustry(String industryName);
}