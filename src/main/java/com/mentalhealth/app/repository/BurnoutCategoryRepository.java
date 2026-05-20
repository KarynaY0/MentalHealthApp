package com.mentalhealth.app.repository;

import com.mentalhealth.app.model.BurnoutCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface BurnoutCategoryRepository extends JpaRepository<BurnoutCategory, Integer> {

    Optional<BurnoutCategory> findByCategoryLabel(String categoryLabel);

    @Query("SELECT b FROM BurnoutCategory b WHERE :score BETWEEN b.minScore AND b.maxScore")
    Optional<BurnoutCategory> findByScore(BigDecimal score);
}