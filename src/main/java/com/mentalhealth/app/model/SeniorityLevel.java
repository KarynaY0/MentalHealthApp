package com.mentalhealth.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "seniority_level")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SeniorityLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seniority_level_id")
    private Integer seniorityLevelId;

    @Column(name = "level_name", nullable = false, unique = true, length = 50)
    private String levelName;

    public SeniorityLevel() {}

    public Integer getSeniorityLevelId() { return seniorityLevelId; }
    public void setSeniorityLevelId(Integer seniorityLevelId) { this.seniorityLevelId = seniorityLevelId; }

    public String getLevelName() { return levelName; }
    public void setLevelName(String levelName) { this.levelName = levelName; }
}