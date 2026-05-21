package com.mentalhealth.app.service;

import com.mentalhealth.app.dto.EmployeeRequest;
import com.mentalhealth.app.model.*;
import com.mentalhealth.app.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepo;
    private final CountryRepository countryRepo;
    private final JobRoleRepository jobRoleRepo;
    private final SeniorityLevelRepository seniorityRepo;
    private final CompanySizeRepository companySizeRepo;
    private final IndustryRepository industryRepo;
    private final WorkModeRepository workModeRepo;
    private final BurnoutCategoryRepository burnoutCategoryRepo;
    private final Phq9CategoryRepository phq9CategoryRepo;
    private final Gad7CategoryRepository gad7CategoryRepo;

    // ── READ ──────────────────────────────────────────────────

    public Page<Employee> getAll(Pageable pageable) {
        return employeeRepo.findAll(pageable);
    }

    public Page<Employee> search(Integer countryId, Integer jobRoleId, Integer industryId,
                                 Integer workModeId, String gender, Pageable pageable) {
        return employeeRepo.findWithFilters(countryId, jobRoleId, industryId, workModeId, gender, pageable);
    }

    public Employee getById(Integer id) {
        return employeeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found: " + id));
    }

    // ── CREATE ────────────────────────────────────────────────

    @Transactional
    public Employee create(EmployeeRequest req) {
        Employee emp = buildEmployee(new Employee(), req);
        Integer id = req.getEmployeeId() != null ? req.getEmployeeId() : getNextEmployeeId();
        emp.setEmployeeId(id);
        return employeeRepo.save(emp);
    }

    // ── UPDATE ────────────────────────────────────────────────

    @Transactional
    public Employee update(Integer id, EmployeeRequest req) {
        Employee emp = getById(id);
        buildEmployee(emp, req);
        return employeeRepo.save(emp);
    }

    // ── DELETE ────────────────────────────────────────────────

    @Transactional
    public void delete(Integer id) {
        if (!employeeRepo.existsById(id)) {
            throw new RuntimeException("Employee not found: " + id);
        }
        employeeRepo.deleteById(id);
    }

    // ── HELPER ───────────────────────────────────────────────

    private Employee buildEmployee(Employee emp, EmployeeRequest req) {
        emp.setAge(req.getAge());
        emp.setGender(req.getGender());
        emp.setYearsExperience(req.getYearsExperience());
        emp.setYearsAtCompany(req.getYearsAtCompany());
        emp.setSalaryUsd(req.getSalaryUsd());

        emp.setCountry(countryRepo.findById(req.getCountryId())
                .orElseThrow(() -> new RuntimeException("Country not found")));
        emp.setJobRole(jobRoleRepo.findById(req.getJobRoleId())
                .orElseThrow(() -> new RuntimeException("JobRole not found")));
        emp.setSeniorityLevel(seniorityRepo.findById(req.getSeniorityLevelId())
                .orElseThrow(() -> new RuntimeException("SeniorityLevel not found")));
        emp.setCompanySize(companySizeRepo.findById(req.getCompanySizeId())
                .orElseThrow(() -> new RuntimeException("CompanySize not found")));
        emp.setIndustry(industryRepo.findById(req.getIndustryId())
                .orElseThrow(() -> new RuntimeException("Industry not found")));
        emp.setWorkMode(workModeRepo.findById(req.getWorkModeId())
                .orElseThrow(() -> new RuntimeException("WorkMode not found")));

        // Work conditions
        WorkConditions wc = emp.getWorkConditions() != null ? emp.getWorkConditions() : new WorkConditions();
        wc.setEmployee(emp);
        wc.setWorkHoursPerWeek(req.getWorkHoursPerWeek());
        wc.setMeetingsPerDay(req.getMeetingsPerDay());
        wc.setTeamSize(req.getTeamSize());
        wc.setDeadlinePressureScore(req.getDeadlinePressureScore());
        wc.setAutonomyScore(req.getAutonomyScore());
        wc.setManagerSupportScore(req.getManagerSupportScore());
        emp.setWorkConditions(wc);

        // Lifestyle
        LifestyleMetrics lm = emp.getLifestyleMetrics() != null ? emp.getLifestyleMetrics() : new LifestyleMetrics();
        lm.setEmployee(emp);
        lm.setSleepHoursPerNight(req.getSleepHoursPerNight());
        lm.setExerciseDaysPerWeek(req.getExerciseDaysPerWeek());
        lm.setVacationDaysTaken(req.getVacationDaysTaken());
        emp.setLifestyleMetrics(lm);

        // Mental health scores
        MentalHealthScores mhs = emp.getMentalHealthScores() != null ? emp.getMentalHealthScores() : new MentalHealthScores();
        mhs.setEmployee(emp);
        mhs.setWorkLifeBalanceScore(req.getWorkLifeBalanceScore());
        mhs.setJobSatisfactionScore(req.getJobSatisfactionScore());
        mhs.setSocialSupportScore(req.getSocialSupportScore());
        mhs.setStressScore(req.getStressScore());
        mhs.setBurnoutScore(req.getBurnoutScore());
        mhs.setBurnoutCategory(burnoutCategoryRepo.findById(req.getBurnoutCategoryId())
                .orElseThrow(() -> new RuntimeException("BurnoutCategory not found")));
        emp.setMentalHealthScores(mhs);

        // Clinical scores
        ClinicalScores cs = emp.getClinicalScores() != null ? emp.getClinicalScores() : new ClinicalScores();
        cs.setEmployee(emp);
        cs.setPhq9Score(req.getPhq9Score());
        cs.setPhq9Category(phq9CategoryRepo.findById(req.getPhq9CategoryId())
                .orElseThrow(() -> new RuntimeException("Phq9Category not found")));
        cs.setGad7Score(req.getGad7Score());
        cs.setGad7Category(gad7CategoryRepo.findById(req.getGad7CategoryId())
                .orElseThrow(() -> new RuntimeException("Gad7Category not found")));
        emp.setClinicalScores(cs);

        // Therapy
        TherapyInfo ti = emp.getTherapyInfo() != null ? emp.getTherapyInfo() : new TherapyInfo();
        ti.setEmployee(emp);
        ti.setTherapyAccess(req.getTherapyAccess());
        ti.setUsesTherapy(req.getUsesTherapy());
        emp.setTherapyInfo(ti);

        // AI tool usage
        AiToolUsage ai = emp.getAiToolUsage() != null ? emp.getAiToolUsage() : new AiToolUsage();
        ai.setEmployee(emp);
        ai.setAiToolsDaily(req.getAiToolsDaily());
        emp.setAiToolUsage(ai);

        // Outcomes
        EmployeeOutcomes eo = emp.getEmployeeOutcomes() != null ? emp.getEmployeeOutcomes() : new EmployeeOutcomes();
        eo.setEmployee(emp);
        eo.setSeeksMentalHealthSupport(req.getSeeksMentalHealthSupport());
        eo.setJobChangeIntention(req.getJobChangeIntention());
        emp.setEmployeeOutcomes(eo);

        return emp;
    }

    public Integer getNextEmployeeId() {
        Integer maxId = employeeRepo.findMaxEmployeeId();
        return (maxId != null ? maxId : 0) + 1;
    }

}