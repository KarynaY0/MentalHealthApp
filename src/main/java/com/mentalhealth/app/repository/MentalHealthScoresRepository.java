package com.mentalhealth.app.repository;

import com.mentalhealth.app.model.MentalHealthScores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface MentalHealthScoresRepository extends JpaRepository<MentalHealthScores, Integer> {

    List<MentalHealthScores> findByBurnoutScoreGreaterThan(BigDecimal burnoutScore);

    List<MentalHealthScores> findByStressScoreGreaterThan(BigDecimal stressScore);

    List<MentalHealthScores> findByBurnoutCategory_CategoryLabel(String categoryLabel);

    @Query("SELECT m FROM MentalHealthScores m ORDER BY m.burnoutScore DESC")
    List<MentalHealthScores> findTopBurnoutEmployees();

    @Query("SELECT AVG(m.burnoutScore) FROM MentalHealthScores m " +
            "JOIN m.employee e JOIN e.industry i WHERE i.industryName = :industryName")
    BigDecimal findAvgBurnoutScoreByIndustry(String industryName);

    @Query("SELECT AVG(m.stressScore) FROM MentalHealthScores m " +
            "JOIN m.employee e JOIN e.workMode w WHERE w.modeName = :modeName")
    BigDecimal findAvgStressScoreByWorkMode(String modeName);

    @Query("SELECT i.industryName, AVG(m.burnoutScore) FROM MentalHealthScores m " +
            "JOIN m.employee e JOIN e.industry i GROUP BY i.industryName ORDER BY AVG(m.burnoutScore) DESC")
    List<Object[]> findAvgBurnoutScoreGroupedByIndustry();

    @Query("SELECT w.modeName, AVG(m.stressScore) FROM MentalHealthScores m " +
            "JOIN m.employee e JOIN e.workMode w GROUP BY w.modeName")
    List<Object[]> findAvgStressScoreGroupedByWorkMode();

    @Query("SELECT bc.categoryLabel, COUNT(m) FROM MentalHealthScores m " +
            "JOIN m.burnoutCategory bc GROUP BY bc.categoryLabel")
    List<Object[]> findBurnoutCategoryDistribution();

    @Query("SELECT s.levelName, AVG(m.burnoutScore) FROM MentalHealthScores m " +
            "JOIN m.employee e JOIN e.seniorityLevel s GROUP BY s.levelName")
    List<Object[]> findAvgBurnoutScoreGroupedBySeniority();
}