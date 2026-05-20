package com.mentalhealth.app.repository;

import com.mentalhealth.app.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
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
}