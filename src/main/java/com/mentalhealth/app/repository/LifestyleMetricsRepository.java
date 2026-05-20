package com.mentalhealth.app.repository;

import com.mentalhealth.app.model.LifestyleMetrics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface LifestyleMetricsRepository extends JpaRepository<LifestyleMetrics, Integer> {

    List<LifestyleMetrics> findBySleepHoursPerNightLessThan(BigDecimal hours);

    List<LifestyleMetrics> findByExerciseDaysPerWeekLessThan(Integer days);

    List<LifestyleMetrics> findByVacationDaysTakenLessThan(Integer days);

    @Query("SELECT AVG(l.sleepHoursPerNight) FROM LifestyleMetrics l")
    BigDecimal findAvgSleepHours();

    @Query("SELECT AVG(l.exerciseDaysPerWeek) FROM LifestyleMetrics l")
    Double findAvgExerciseDays();
}