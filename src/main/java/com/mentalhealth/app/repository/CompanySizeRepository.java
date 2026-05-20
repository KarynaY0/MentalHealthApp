package com.mentalhealth.app.repository;

import com.mentalhealth.app.model.CompanySize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanySizeRepository extends JpaRepository<CompanySize, Integer> {

    Optional<CompanySize> findBySizeLabel(String sizeLabel);
}