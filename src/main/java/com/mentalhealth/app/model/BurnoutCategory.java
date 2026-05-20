package com.mentalhealth.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "burnout_category")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BurnoutCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "burnout_category_id")
    private Integer burnoutCategoryId;

    @Column(name = "category_label", nullable = false, unique = true, length = 50)
    private String categoryLabel;

    @Column(name = "min_score", nullable = false, precision = 5, scale = 2)
    private BigDecimal minScore;

    @Column(name = "max_score", nullable = false, precision = 5, scale = 2)
    private BigDecimal maxScore;

    public BurnoutCategory() {}

    public Integer getBurnoutCategoryId() { return burnoutCategoryId; }
    public void setBurnoutCategoryId(Integer burnoutCategoryId) { this.burnoutCategoryId = burnoutCategoryId; }

    public String getCategoryLabel() { return categoryLabel; }
    public void setCategoryLabel(String categoryLabel) { this.categoryLabel = categoryLabel; }

    public BigDecimal getMinScore() { return minScore; }
    public void setMinScore(BigDecimal minScore) { this.minScore = minScore; }

    public BigDecimal getMaxScore() { return maxScore; }
    public void setMaxScore(BigDecimal maxScore) { this.maxScore = maxScore; }
}