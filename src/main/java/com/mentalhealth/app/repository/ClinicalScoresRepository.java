package com.mentalhealth.app.repository;

import com.mentalhealth.app.model.ClinicalScores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClinicalScoresRepository extends JpaRepository<ClinicalScores, Integer> {

    List<ClinicalScores> findByPhq9ScoreGreaterThan(Integer score);

    List<ClinicalScores> findByGad7ScoreGreaterThan(Integer score);

    List<ClinicalScores> findByPhq9Category_CategoryLabel(String categoryLabel);

    List<ClinicalScores> findByGad7Category_CategoryLabel(String categoryLabel);

    @Query("SELECT pc.categoryLabel, COUNT(c) FROM ClinicalScores c " +
            "JOIN c.phq9Category pc GROUP BY pc.categoryLabel")
    List<Object[]> findPhq9CategoryDistribution();

    @Query("SELECT gc.categoryLabel, COUNT(c) FROM ClinicalScores c " +
            "JOIN c.gad7Category gc GROUP BY gc.categoryLabel")
    List<Object[]> findGad7CategoryDistribution();

    @Query("SELECT c FROM ClinicalScores c WHERE c.phq9Category.categoryLabel IN ('Moderately Severe', 'Severe') " +
            "OR c.gad7Category.categoryLabel IN ('Moderately Severe', 'Severe')")
    List<ClinicalScores> findSevereEmployees();
}