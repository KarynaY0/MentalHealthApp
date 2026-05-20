package com.mentalhealth.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "industry")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Industry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "industry_id")
    private Integer industryId;

    @Column(name = "industry_name", nullable = false, unique = true, length = 100)
    private String industryName;

    public Industry() {}

    public Integer getIndustryId() { return industryId; }
    public void setIndustryId(Integer industryId) { this.industryId = industryId; }

    public String getIndustryName() { return industryName; }
    public void setIndustryName(String industryName) { this.industryName = industryName; }
}