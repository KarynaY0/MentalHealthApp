package com.mentalhealth.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "work_mode")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class WorkMode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "work_mode_id")
    private Integer workModeId;

    @Column(name = "mode_name", nullable = false, unique = true, length = 50)
    private String modeName;

    public WorkMode() {}

    public Integer getWorkModeId() { return workModeId; }
    public void setWorkModeId(Integer workModeId) { this.workModeId = workModeId; }

    public String getModeName() { return modeName; }
    public void setModeName(String modeName) { this.modeName = modeName; }
}