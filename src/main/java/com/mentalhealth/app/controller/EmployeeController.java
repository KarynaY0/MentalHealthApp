package com.mentalhealth.app.controller;

import com.mentalhealth.app.dto.EmployeeRequest;
import com.mentalhealth.app.model.Employee;
import com.mentalhealth.app.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService service;

    // GET /api/employees?page=0&size=20
    @GetMapping
    public Page<Employee> getAll(@PageableDefault(size = 20) Pageable pageable) {
        return service.getAll(pageable);
    }

    // GET /api/employees/search?countryId=1&jobRoleId=2&page=0&size=20
    @GetMapping("/search")
    public Page<Employee> search(
            @RequestParam(required = false) Integer countryId,
            @RequestParam(required = false) Integer jobRoleId,
            @RequestParam(required = false) Integer industryId,
            @RequestParam(required = false) Integer workModeId,
            @RequestParam(required = false) String gender,
            @PageableDefault(size = 20) Pageable pageable) {
        return service.search(countryId, jobRoleId, industryId, workModeId, gender, pageable);
    }

    // GET /api/employees/100001
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    // POST /api/employees
    @PostMapping
    public ResponseEntity<Employee> create(@Valid @RequestBody EmployeeRequest req) {
        return ResponseEntity.ok(service.create(req));
    }

    // PUT /api/employees/100001
    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable Integer id,
                                           @Valid @RequestBody EmployeeRequest req) {
        return ResponseEntity.ok(service.update(id, req));
    }

    // DELETE /api/employees/100001
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}