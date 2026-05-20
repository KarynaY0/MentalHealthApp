package com.mentalhealth.app.repository;

import com.mentalhealth.app.model.Industry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IndustryRepository extends JpaRepository<Industry, Integer> {

    Optional<Industry> findByIndustryName(String industryName);
}