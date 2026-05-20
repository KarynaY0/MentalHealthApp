package com.mentalhealth.app.repository;

import com.mentalhealth.app.model.AiToolUsage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AiToolUsageRepository extends JpaRepository<AiToolUsage, Integer> {

    List<AiToolUsage> findByAiToolsDailyTrue();

    List<AiToolUsage> findByAiToolsDailyFalse();

    @Query("SELECT COUNT(a) FROM AiToolUsage a WHERE a.aiToolsDaily = true")
    Long countByAiToolsDailyTrue();

    @Query("SELECT r.roleName, " +
            "SUM(CASE WHEN a.aiToolsDaily = true THEN 1 ELSE 0 END) * 1.0 / COUNT(a) " +
            "FROM AiToolUsage a JOIN a.employee e JOIN e.jobRole r GROUP BY r.roleName")
    List<Object[]> findAiUsageRateGroupedByJobRole();
}