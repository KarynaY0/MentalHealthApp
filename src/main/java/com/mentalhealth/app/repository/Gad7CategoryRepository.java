package com.mentalhealth.app.repository;

import com.mentalhealth.app.model.Gad7Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Gad7CategoryRepository extends JpaRepository<Gad7Category, Integer> {

    Optional<Gad7Category> findByCategoryLabel(String categoryLabel);

    @Query("SELECT g FROM Gad7Category g WHERE :score BETWEEN g.minScore AND g.maxScore")
    Optional<Gad7Category> findByScore(Integer score);
}