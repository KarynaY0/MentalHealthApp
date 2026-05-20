package com.mentalhealth.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "gad7_category")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Gad7Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gad7_category_id")
    private Integer gad7CategoryId;

    @Column(name = "category_label", nullable = false, unique = true, length = 50)
    private String categoryLabel;

    @Column(name = "min_score", nullable = false)
    private Integer minScore;

    @Column(name = "max_score", nullable = false)
    private Integer maxScore;

    public Gad7Category() {}

    public Integer getGad7CategoryId() { return gad7CategoryId; }
    public void setGad7CategoryId(Integer gad7CategoryId) { this.gad7CategoryId = gad7CategoryId; }

    public String getCategoryLabel() { return categoryLabel; }
    public void setCategoryLabel(String categoryLabel) { this.categoryLabel = categoryLabel; }

    public Integer getMinScore() { return minScore; }
    public void setMinScore(Integer minScore) { this.minScore = minScore; }

    public Integer getMaxScore() { return maxScore; }
    public void setMaxScore(Integer maxScore) { this.maxScore = maxScore; }
}