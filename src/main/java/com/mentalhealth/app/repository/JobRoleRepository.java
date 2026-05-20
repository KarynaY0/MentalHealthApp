package com.mentalhealth.app.repository;

import com.mentalhealth.app.model.JobRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobRoleRepository extends JpaRepository<JobRole, Integer> {

    Optional<JobRole> findByRoleName(String roleName);
}