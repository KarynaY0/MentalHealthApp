package com.mentalhealth.app.repository;

import com.mentalhealth.app.model.Phq9Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Phq9CategoryRepository extends JpaRepository<Phq9Category, Integer> {

    Optional<Phq9Category> findByCategoryLabel(String categoryLabel);

    @Query("SELECT p FROM Phq9Category p WHERE :score BETWEEN p.minScore AND p.maxScore")
    Optional<Phq9Category> findByScore(Integer score);
}