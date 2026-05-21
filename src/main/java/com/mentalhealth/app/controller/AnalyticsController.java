package com.mentalhealth.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/analytics")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AnalyticsController {

    private final JdbcTemplate jdbc;

    // ── Mental Health Page ────────────────────────────────────

    // Average burnout score per industry
    // Optional filter: ?industryId=1&seniorityLevelId=2
        @GetMapping("/burnout-by-industry")
    public List<Map<String, Object>> burnoutByIndustry(
            @RequestParam(required = false) Integer industryId,
            @RequestParam(required = false) Integer seniorityLevelId) {
        return jdbc.queryForList("""
            SELECT
                i.industry_name,
                ROUND(AVG(mhs.burnout_score)::NUMERIC, 2) AS avg_burnout,
                COUNT(e.employee_id) AS employee_count
            FROM employee e
            JOIN industry i             ON e.industry_id    = i.industry_id
            JOIN mental_health_scores mhs ON e.employee_id  = mhs.employee_id
            WHERE (:industryId IS NULL OR e.industry_id = :industryId)
              AND (:seniorityLevelId IS NULL OR e.seniority_level_id = :seniorityLevelId)
            GROUP BY i.industry_name
            ORDER BY avg_burnout DESC
            """.replace(":industryId",       industryId       == null ? "NULL" : industryId.toString())
                .replace(":seniorityLevelId", seniorityLevelId == null ? "NULL" : seniorityLevelId.toString())
        );
    }

    // Average stress score per work mode
    @GetMapping("/stress-by-work-mode")
    public List<Map<String, Object>> stressByWorkMode(
            @RequestParam(required = false) Integer industryId,
            @RequestParam(required = false) Integer seniorityLevelId) {
        return jdbc.queryForList("""
            SELECT
                wm.mode_name AS work_mode,
                ROUND(AVG(mhs.stress_score)::NUMERIC, 2) AS avg_stress,
                COUNT(e.employee_id) AS employee_count
            FROM employee e
            JOIN work_mode wm             ON e.work_mode_id   = wm.work_mode_id
            JOIN mental_health_scores mhs ON e.employee_id    = mhs.employee_id
            WHERE (:industryId IS NULL OR e.industry_id = :industryId)
              AND (:seniorityLevelId IS NULL OR e.seniority_level_id = :seniorityLevelId)
            GROUP BY wm.mode_name
            ORDER BY avg_stress DESC
            """.replace(":industryId",       industryId       == null ? "NULL" : industryId.toString())
                .replace(":seniorityLevelId", seniorityLevelId == null ? "NULL" : seniorityLevelId.toString())
        );
    }

    // Burnout category distribution (count per category)
    @GetMapping("/burnout-category-distribution")
    public List<Map<String, Object>> burnoutCategoryDistribution(
            @RequestParam(required = false) Integer industryId,
            @RequestParam(required = false) Integer seniorityLevelId) {
        return jdbc.queryForList("""
            SELECT
                bc.category_label,
                COUNT(e.employee_id) AS employee_count,
                ROUND(100.0 * COUNT(e.employee_id) / SUM(COUNT(e.employee_id)) OVER (), 1) AS percentage
            FROM employee e
            JOIN mental_health_scores mhs ON e.employee_id          = mhs.employee_id
            JOIN burnout_category bc      ON mhs.burnout_category_id = bc.burnout_category_id
            WHERE (:industryId IS NULL OR e.industry_id = :industryId)
              AND (:seniorityLevelId IS NULL OR e.seniority_level_id = :seniorityLevelId)
            GROUP BY bc.category_label, bc.burnout_category_id
            ORDER BY bc.burnout_category_id
            """.replace(":industryId",       industryId       == null ? "NULL" : industryId.toString())
                .replace(":seniorityLevelId", seniorityLevelId == null ? "NULL" : seniorityLevelId.toString())
        );
    }

    // Summary stats for mental health page header cards
    @GetMapping("/mental-health-summary")
    public Map<String, Object> mentalHealthSummary() {
        return jdbc.queryForMap("""
            SELECT
                ROUND(AVG(mhs.burnout_score)::NUMERIC, 2)  AS avg_burnout,
                ROUND(AVG(mhs.stress_score)::NUMERIC, 2)   AS avg_stress,
                ROUND(AVG(mhs.job_satisfaction_score)::NUMERIC, 2) AS avg_job_satisfaction,
                ROUND(AVG(mhs.work_life_balance_score)::NUMERIC, 2) AS avg_work_life_balance,
                COUNT(*) FILTER (WHERE bc.category_label = 'Severe') AS severe_burnout_count
            FROM mental_health_scores mhs
            JOIN burnout_category bc ON mhs.burnout_category_id = bc.burnout_category_id
            """);
    }

    // ── Clinical Page ─────────────────────────────────────────

    // PHQ-9 score distribution per category
    @GetMapping("/phq9-distribution")
    public List<Map<String, Object>> phq9Distribution() {
        return jdbc.queryForList("""
            SELECT
                pc.category_label,
                pc.min_score,
                pc.max_score,
                COUNT(cs.employee_id) AS employee_count,
                ROUND(100.0 * COUNT(cs.employee_id) / SUM(COUNT(cs.employee_id)) OVER (), 1) AS percentage
            FROM clinical_scores cs
            JOIN phq9_category pc ON cs.phq9_category_id = pc.phq9_category_id
            GROUP BY pc.category_label, pc.min_score, pc.max_score, pc.phq9_category_id
            ORDER BY pc.phq9_category_id
            """);
    }

    // GAD-7 score distribution per category
    @GetMapping("/gad7-distribution")
    public List<Map<String, Object>> gad7Distribution() {
        return jdbc.queryForList("""
            SELECT
                gc.category_label,
                gc.min_score,
                gc.max_score,
                COUNT(cs.employee_id) AS employee_count,
                ROUND(100.0 * COUNT(cs.employee_id) / SUM(COUNT(cs.employee_id)) OVER (), 1) AS percentage
            FROM clinical_scores cs
            JOIN gad7_category gc ON cs.gad7_category_id = gc.gad7_category_id
            GROUP BY gc.category_label, gc.min_score, gc.max_score, gc.gad7_category_id
            ORDER BY gc.gad7_category_id
            """);
    }

    // High-risk employees: Moderately Severe or Severe PHQ-9 or GAD-7
    // with therapy access status — paginated
    @GetMapping("/high-risk-employees")
    public Map<String, Object> highRiskEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        int offset = page * size;

        List<Map<String, Object>> rows = jdbc.queryForList("""
            SELECT
                e.employee_id,
                e.age,
                e.gender,
                jr.role_name        AS job_role,
                i.industry_name,
                cs.phq9_score,
                pc.category_label   AS phq9_category,
                cs.gad7_score,
                gc.category_label   AS gad7_category,
                ti.therapy_access,
                ti.uses_therapy,
                mhs.burnout_score,
                bc.category_label   AS burnout_category
            FROM employee e
            JOIN job_role jr             ON e.job_role_id         = jr.job_role_id
            JOIN industry i              ON e.industry_id         = i.industry_id
            JOIN clinical_scores cs      ON e.employee_id         = cs.employee_id
            JOIN phq9_category pc        ON cs.phq9_category_id   = pc.phq9_category_id
            JOIN gad7_category gc        ON cs.gad7_category_id   = gc.gad7_category_id
            JOIN therapy_info ti         ON e.employee_id         = ti.employee_id
            JOIN mental_health_scores mhs ON e.employee_id        = mhs.employee_id
            JOIN burnout_category bc     ON mhs.burnout_category_id = bc.burnout_category_id
            WHERE pc.category_label IN ('Moderately Severe (15-19)', 'Severe (20-27)')
               OR gc.category_label = 'Severe (15-21)'
            ORDER BY cs.phq9_score DESC, cs.gad7_score DESC
            LIMIT ? OFFSET ?
            """, size, offset);

        Integer total = jdbc.queryForObject("""
            SELECT COUNT(*)
            FROM employee e
            JOIN clinical_scores cs   ON e.employee_id       = cs.employee_id
            JOIN phq9_category pc     ON cs.phq9_category_id = pc.phq9_category_id
            JOIN gad7_category gc     ON cs.gad7_category_id = gc.gad7_category_id
            WHERE pc.category_label IN ('Moderately Severe (15-19)', 'Severe (20-27)')
               OR gc.category_label = 'Severe (15-21)'
            """, Integer.class);

        return Map.of(
                "content",       rows,
                "totalElements", total != null ? total : 0,
                "totalPages",    total != null ? (int) Math.ceil((double) total / size) : 0,
                "page",          page,
                "size",          size
        );
    }

    // Clinical summary header cards
    @GetMapping("/clinical-summary")
    public Map<String, Object> clinicalSummary() {
        return jdbc.queryForMap("""
            SELECT
                ROUND(AVG(cs.phq9_score)::NUMERIC, 2) AS avg_phq9,
                ROUND(AVG(cs.gad7_score)::NUMERIC, 2) AS avg_gad7,
                COUNT(*) FILTER (WHERE pc.category_label IN ('Moderately Severe (15-19)', 'Severe (20-27)')) AS high_risk_depression,
                COUNT(*) FILTER (WHERE gc.category_label = 'Severe (15-21)') AS severe_anxiety,
                COUNT(*) FILTER (
                    WHERE (pc.category_label IN ('Moderately Severe (15-19)', 'Severe (20-27)')
                        OR gc.category_label = 'Severe (15-21)')
                    AND ti.therapy_access = false
                ) AS high_risk_no_therapy
            FROM clinical_scores cs
            JOIN phq9_category pc ON cs.phq9_category_id = pc.phq9_category_id
            JOIN gad7_category gc ON cs.gad7_category_id = gc.gad7_category_id
            JOIN therapy_info ti  ON cs.employee_id      = ti.employee_id
            """);
    }
}