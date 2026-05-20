package com.mentalhealth.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "company_size")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CompanySize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_size_id")
    private Integer companySizeId;

    @Column(name = "size_label", nullable = false, unique = true, length = 50)
    private String sizeLabel;

    @Column(name = "min_employees", nullable = false)
    private Integer minEmployees;

    @Column(name = "max_employees")
    private Integer maxEmployees;

    public CompanySize() {}

    public Integer getCompanySizeId() { return companySizeId; }
    public void setCompanySizeId(Integer companySizeId) { this.companySizeId = companySizeId; }

    public String getSizeLabel() { return sizeLabel; }
    public void setSizeLabel(String sizeLabel) { this.sizeLabel = sizeLabel; }

    public Integer getMinEmployees() { return minEmployees; }
    public void setMinEmployees(Integer minEmployees) { this.minEmployees = minEmployees; }

    public Integer getMaxEmployees() { return maxEmployees; }
    public void setMaxEmployees(Integer maxEmployees) { this.maxEmployees = maxEmployees; }
}