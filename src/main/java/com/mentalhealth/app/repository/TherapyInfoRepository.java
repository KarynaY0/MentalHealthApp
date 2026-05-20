package com.mentalhealth.app.repository;

import com.mentalhealth.app.model.TherapyInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TherapyInfoRepository extends JpaRepository<TherapyInfo, Integer> {

    List<TherapyInfo> findByTherapyAccessTrueAndUsesTherapyFalse();

    List<TherapyInfo> findByUsesTherapyTrue();

    List<TherapyInfo> findByTherapyAccessFalse();

    @Query("SELECT COUNT(t) FROM TherapyInfo t WHERE t.therapyAccess = true AND t.usesTherapy = false")
    Long countTherapyGap();

    @Query("SELECT i.industryName, COUNT(t) FROM TherapyInfo t " +
            "JOIN t.employee e JOIN e.industry i " +
            "WHERE t.therapyAccess = true AND t.usesTherapy = false " +
            "GROUP BY i.industryName")
    List<Object[]> findTherapyGapGroupedByIndustry();
}