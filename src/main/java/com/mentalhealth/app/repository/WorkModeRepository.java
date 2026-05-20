package com.mentalhealth.app.repository;

import com.mentalhealth.app.model.WorkMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkModeRepository extends JpaRepository<WorkMode, Integer> {

    Optional<WorkMode> findByModeName(String modeName);
}