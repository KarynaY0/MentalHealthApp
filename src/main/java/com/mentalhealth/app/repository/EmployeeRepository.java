package com.mentalhealth.app.repository;

import com.mentalhealth.app.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findByCountry_CountryName(String countryName);

    List<Employee> findByJobRole_RoleName(String roleName);

    List<Employee> findBySalaryUsdGreaterThan(BigDecimal salary);

    List<Employee> findByIndustry_IndustryName(String industryName);

    List<Employee> findBySeniorityLevel_LevelName(String levelName);

    List<Employee> findByWorkMode_ModeName(String modeName);

    List<Employee> findByCompanySize_SizeLabel(String sizeLabel);

    List<Employee> findByGender(String gender);

    List<Employee> findByAgeBetween(Integer minAge, Integer maxAge);

    // Combined search
    @Query("""
        SELECT e FROM Employee e
        WHERE (:countryId IS NULL OR e.country.countryId = :countryId)
          AND (:jobRoleId IS NULL OR e.jobRole.jobRoleId = :jobRoleId)
          AND (:industryId IS NULL OR e.industry.industryId = :industryId)
          AND (:workModeId IS NULL OR e.workMode.workModeId = :workModeId)
          AND (:gender IS NULL OR e.gender = :gender)
    """)
    Page<Employee> findWithFilters(
            @Param("countryId") Integer countryId,
            @Param("jobRoleId") Integer jobRoleId,
            @Param("industryId") Integer industryId,
            @Param("workModeId") Integer workModeId,
            @Param("gender") String gender,
            Pageable pageable
    );

    @Query("SELECT MAX(e.employeeId) FROM Employee e")
    Integer findMaxEmployeeId();

}