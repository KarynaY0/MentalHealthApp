package com.mentalhealth.app.repository;

import com.mentalhealth.app.model.SeniorityLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeniorityLevelRepository extends JpaRepository<SeniorityLevel, Integer> {

    Optional<SeniorityLevel> findByLevelName(String levelName);
}