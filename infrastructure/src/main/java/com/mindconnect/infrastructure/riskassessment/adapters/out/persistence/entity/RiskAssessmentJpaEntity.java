package com.mindconnect.infrastructure.riskassessment.adapters.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidad JPA de la tabla risk_assessments. Vive solo en infraestructura: el dominio no la conoce.
 *
 * <p>Las llaves foráneas se guardan como UUID simples (sin @ManyToOne), igual que en el
 * modelo de dominio, que se relaciona con otros agregados solo por id.</p>
 */
@Entity
@Table(name = "risk_assessments")
public class RiskAssessmentJpaEntity {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "encounter_id", nullable = false)
    private UUID encounterId;

    @Column(name = "risk_level_id", nullable = false)
    private UUID riskLevelId;

    @Column(name = "suicidal_ideation", nullable = false)
    private Boolean suicidalIdeation;

    @Column(name = "suicide_plan", nullable = false)
    private Boolean suicidePlan;

    @Column(name = "suicide_intent", nullable = false)
    private Boolean suicideIntent;

    @Column(name = "self_harm", nullable = false)
    private Boolean selfHarm;

    @Column(name = "harm_to_others", nullable = false)
    private Boolean harmToOthers;

    @Column(name = "risk_factors", nullable = false, columnDefinition = "TEXT")
    private String riskFactors;

    @Column(name = "protective_factors", nullable = false, columnDefinition = "TEXT")
    private String protectiveFactors;

    @Column(name = "clinical_actions", nullable = false, columnDefinition = "TEXT")
    private String clinicalActions;

    @Column(name = "observations", nullable = false, columnDefinition = "TEXT")
    private String observations;

    @Column(name = "assessed_at", nullable = false)
    private LocalDateTime assessedAt;

    @Column(name = "assessed_by", nullable = false)
    private UUID assessedBy;

    /** JPA exige un constructor sin argumentos. */
    public RiskAssessmentJpaEntity() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getEncounterId() {
        return encounterId;
    }

    public void setEncounterId(UUID encounterId) {
        this.encounterId = encounterId;
    }

    public UUID getRiskLevelId() {
        return riskLevelId;
    }

    public void setRiskLevelId(UUID riskLevelId) {
        this.riskLevelId = riskLevelId;
    }

    public Boolean getSuicidalIdeation() {
        return suicidalIdeation;
    }

    public void setSuicidalIdeation(Boolean suicidalIdeation) {
        this.suicidalIdeation = suicidalIdeation;
    }

    public Boolean getSuicidePlan() {
        return suicidePlan;
    }

    public void setSuicidePlan(Boolean suicidePlan) {
        this.suicidePlan = suicidePlan;
    }

    public Boolean getSuicideIntent() {
        return suicideIntent;
    }

    public void setSuicideIntent(Boolean suicideIntent) {
        this.suicideIntent = suicideIntent;
    }

    public Boolean getSelfHarm() {
        return selfHarm;
    }

    public void setSelfHarm(Boolean selfHarm) {
        this.selfHarm = selfHarm;
    }

    public Boolean getHarmToOthers() {
        return harmToOthers;
    }

    public void setHarmToOthers(Boolean harmToOthers) {
        this.harmToOthers = harmToOthers;
    }

    public String getRiskFactors() {
        return riskFactors;
    }

    public void setRiskFactors(String riskFactors) {
        this.riskFactors = riskFactors;
    }

    public String getProtectiveFactors() {
        return protectiveFactors;
    }

    public void setProtectiveFactors(String protectiveFactors) {
        this.protectiveFactors = protectiveFactors;
    }

    public String getClinicalActions() {
        return clinicalActions;
    }

    public void setClinicalActions(String clinicalActions) {
        this.clinicalActions = clinicalActions;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public LocalDateTime getAssessedAt() {
        return assessedAt;
    }

    public void setAssessedAt(LocalDateTime assessedAt) {
        this.assessedAt = assessedAt;
    }

    public UUID getAssessedBy() {
        return assessedBy;
    }

    public void setAssessedBy(UUID assessedBy) {
        this.assessedBy = assessedBy;
    }
}
