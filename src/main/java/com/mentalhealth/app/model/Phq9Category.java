package com.mentalhealth.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "phq9_category")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Phq9Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "phq9_category_id")
    private Integer phq9CategoryId;

    @Column(name = "category_label", nullable = false, unique = true, length = 50)
    private String categoryLabel;

    @Column(name = "min_score", nullable = false)
    private Integer minScore;

    @Column(name = "max_score", nullable = false)
    private Integer maxScore;

    public Phq9Category() {}

    public Integer getPhq9CategoryId() { return phq9CategoryId; }
    public void setPhq9CategoryId(Integer phq9CategoryId) { this.phq9CategoryId = phq9CategoryId; }

    public String getCategoryLabel() { return categoryLabel; }
    public void setCategoryLabel(String categoryLabel) { this.categoryLabel = categoryLabel; }

    public Integer getMinScore() { return minScore; }
    public void setMinScore(Integer minScore) { this.minScore = minScore; }

    public Integer getMaxScore() { return maxScore; }
    public void setMaxScore(Integer maxScore) { this.maxScore = maxScore; }
}