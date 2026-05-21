package com.mentalhealth.app.controller;

import com.mentalhealth.app.model.*;
import com.mentalhealth.app.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lookup")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class LookupController {

    private final CountryRepository countryRepo;
    private final JobRoleRepository jobRoleRepo;
    private final SeniorityLevelRepository seniorityRepo;
    private final CompanySizeRepository companySizeRepo;
    private final IndustryRepository industryRepo;
    private final WorkModeRepository workModeRepo;
    private final BurnoutCategoryRepository burnoutCategoryRepo;
    private final Phq9CategoryRepository phq9CategoryRepo;
    private final Gad7CategoryRepository gad7CategoryRepo;

    @GetMapping("/countries")
    public List<Country> countries() { return countryRepo.findAll(); }

    @GetMapping("/job-roles")
    public List<JobRole> jobRoles() { return jobRoleRepo.findAll(); }

    @GetMapping("/seniority-levels")
    public List<SeniorityLevel> seniorityLevels() { return seniorityRepo.findAll(); }

    @GetMapping("/company-sizes")
    public List<CompanySize> companySizes() { return companySizeRepo.findAll(); }

    @GetMapping("/industries")
    public List<Industry> industries() { return industryRepo.findAll(); }

    @GetMapping("/work-modes")
    public List<WorkMode> workModes() { return workModeRepo.findAll(); }

    @GetMapping("/burnout-categories")
    public List<BurnoutCategory> burnoutCategories() { return burnoutCategoryRepo.findAll(); }

    @GetMapping("/phq9-categories")
    public List<Phq9Category> phq9Categories() { return phq9CategoryRepo.findAll(); }

    @GetMapping("/gad7-categories")
    public List<Gad7Category> gad7Categories() { return gad7CategoryRepo.findAll(); }
}