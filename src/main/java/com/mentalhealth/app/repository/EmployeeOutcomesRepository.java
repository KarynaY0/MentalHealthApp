package com.mentalhealth.app.repository;

import com.mentalhealth.app.model.EmployeeOutcomes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeOutcomesRepository extends JpaRepository<EmployeeOutcomes, Integer> {

    List<EmployeeOutcomes> findByJobChangeIntentionTrue();

    List<EmployeeOutcomes> findBySeeksMentalHealthSupportTrue();

    @Query("SELECT COUNT(o) FROM EmployeeOutcomes o WHERE o.jobChangeIntention = true")
    Long countByJobChangeIntentionTrue();

    @Query("SELECT COUNT(o) FROM EmployeeOutcomes o WHERE o.seeksMentalHealthSupport = true")
    Long countBySeeksMentalHealthSupportTrue();

    @Query("SELECT i.industryName, " +
            "SUM(CASE WHEN o.jobChangeIntention = true THEN 1 ELSE 0 END) * 1.0 / COUNT(o) " +
            "FROM EmployeeOutcomes o JOIN o.employee e JOIN e.industry i GROUP BY i.industryName")
    List<Object[]> findJobChangeRateGroupedByIndustry();

    @Query("SELECT w.modeName, " +
            "SUM(CASE WHEN o.seeksMentalHealthSupport = true THEN 1 ELSE 0 END) * 1.0 / COUNT(o) " +
            "FROM EmployeeOutcomes o JOIN o.employee e JOIN e.workMode w GROUP BY w.modeName")
    List<Object[]> findSupportSeekingRateGroupedByWorkMode();
}