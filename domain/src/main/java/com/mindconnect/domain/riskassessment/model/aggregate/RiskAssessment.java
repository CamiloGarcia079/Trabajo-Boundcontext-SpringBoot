package com.mindconnect.domain.riskassessment.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import com.mindconnect.domain.common.model.AggregateRoot;
import com.mindconnect.domain.common.validation.DomainValidations;
import com.mindconnect.domain.riskassessment.event.RiskAssessmentRegisteredEvent;
import com.mindconnect.domain.riskassessment.event.RiskAssessmentUpdatedEvent;
import com.mindconnect.domain.riskassessment.model.valueobject.RiskAssessmentId;

/**
 * Agregado raíz del contexto riskassessment: representa la tabla risk_assessments.
 *
 * <p>Relaciones (se guardan solo por id, no como objetos):</p>
 * <ul>
 *   <li>encounterId -> Encounter</li>
 *   <li>riskLevelId -> RiskLevel</li>
 *   <li>assessedBy -> Professional</li>
 * </ul>
 *
 * <p>El id y las fechas los pone el propio agregado. No usa Spring ni JPA.</p>
 */
public class RiskAssessment extends AggregateRoot {

    private final RiskAssessmentId id;
    private UUID encounterId;
    private UUID riskLevelId;
    private boolean suicidalIdeation;
    private boolean suicidePlan;
    private boolean suicideIntent;
    private boolean selfHarm;
    private boolean harmToOthers;
    private String riskFactors;
    private String protectiveFactors;
    private String clinicalActions;
    private String observations;
    private LocalDateTime assessedAt;
    private UUID assessedBy;

    private RiskAssessment(
            RiskAssessmentId id,
            UUID encounterId,
            UUID riskLevelId,
            boolean suicidalIdeation,
            boolean suicidePlan,
            boolean suicideIntent,
            boolean selfHarm,
            boolean harmToOthers,
            String riskFactors,
            String protectiveFactors,
            String clinicalActions,
            String observations,
            LocalDateTime assessedAt,
            UUID assessedBy) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        DomainValidations.required(encounterId, "encounterId");
        DomainValidations.required(riskLevelId, "riskLevelId");
        DomainValidations.required(riskFactors, "riskFactors");
        DomainValidations.required(protectiveFactors, "protectiveFactors");
        DomainValidations.required(clinicalActions, "clinicalActions");
        DomainValidations.required(observations, "observations");
        DomainValidations.required(assessedAt, "assessedAt");
        DomainValidations.required(assessedBy, "assessedBy");
        this.encounterId = encounterId;
        this.riskLevelId = riskLevelId;
        this.suicidalIdeation = suicidalIdeation;
        this.suicidePlan = suicidePlan;
        this.suicideIntent = suicideIntent;
        this.selfHarm = selfHarm;
        this.harmToOthers = harmToOthers;
        this.riskFactors = riskFactors;
        this.protectiveFactors = protectiveFactors;
        this.clinicalActions = clinicalActions;
        this.observations = observations;
        this.assessedAt = assessedAt;
        this.assessedBy = assessedBy;
    }

    /** Crea un registro nuevo: genera el id y las fechas, y deja el evento de registro. */
    public static RiskAssessment register(
            UUID encounterId,
            UUID riskLevelId,
            boolean suicidalIdeation,
            boolean suicidePlan,
            boolean suicideIntent,
            boolean selfHarm,
            boolean harmToOthers,
            String riskFactors,
            String protectiveFactors,
            String clinicalActions,
            String observations,
            LocalDateTime assessedAt,
            UUID assessedBy) {
        RiskAssessmentId id = RiskAssessmentId.generate();
        LocalDateTime now = LocalDateTime.now();
        RiskAssessment aggregate = new RiskAssessment(id, encounterId, riskLevelId, suicidalIdeation, suicidePlan, suicideIntent, selfHarm, harmToOthers, riskFactors, protectiveFactors, clinicalActions, observations, assessedAt, assessedBy);
        aggregate.recordEvent(new RiskAssessmentRegisteredEvent(id, now));
        return aggregate;
    }

    /** Reconstruye un registro que ya existía (viene de la base de datos). No genera eventos. */
    public static RiskAssessment restore(
            RiskAssessmentId id,
            UUID encounterId,
            UUID riskLevelId,
            boolean suicidalIdeation,
            boolean suicidePlan,
            boolean suicideIntent,
            boolean selfHarm,
            boolean harmToOthers,
            String riskFactors,
            String protectiveFactors,
            String clinicalActions,
            String observations,
            LocalDateTime assessedAt,
            UUID assessedBy) {
        return new RiskAssessment(id, encounterId, riskLevelId, suicidalIdeation, suicidePlan, suicideIntent, selfHarm, harmToOthers, riskFactors, protectiveFactors, clinicalActions, observations, assessedAt, assessedBy);
    }

    /** Cambia los datos del registro, actualiza la fecha de modificación y deja el evento. */
    public void update(
            UUID encounterId,
            UUID riskLevelId,
            boolean suicidalIdeation,
            boolean suicidePlan,
            boolean suicideIntent,
            boolean selfHarm,
            boolean harmToOthers,
            String riskFactors,
            String protectiveFactors,
            String clinicalActions,
            String observations,
            LocalDateTime assessedAt,
            UUID assessedBy) {
        DomainValidations.required(encounterId, "encounterId");
        DomainValidations.required(riskLevelId, "riskLevelId");
        DomainValidations.required(riskFactors, "riskFactors");
        DomainValidations.required(protectiveFactors, "protectiveFactors");
        DomainValidations.required(clinicalActions, "clinicalActions");
        DomainValidations.required(observations, "observations");
        DomainValidations.required(assessedAt, "assessedAt");
        DomainValidations.required(assessedBy, "assessedBy");
        this.encounterId = encounterId;
        this.riskLevelId = riskLevelId;
        this.suicidalIdeation = suicidalIdeation;
        this.suicidePlan = suicidePlan;
        this.suicideIntent = suicideIntent;
        this.selfHarm = selfHarm;
        this.harmToOthers = harmToOthers;
        this.riskFactors = riskFactors;
        this.protectiveFactors = protectiveFactors;
        this.clinicalActions = clinicalActions;
        this.observations = observations;
        this.assessedAt = assessedAt;
        this.assessedBy = assessedBy;
        LocalDateTime now = LocalDateTime.now();
        recordEvent(new RiskAssessmentUpdatedEvent(this.id, now));
    }

    public RiskAssessmentId id() { return id; }
    public UUID encounterId() { return encounterId; }
    public UUID riskLevelId() { return riskLevelId; }
    public boolean suicidalIdeation() { return suicidalIdeation; }
    public boolean suicidePlan() { return suicidePlan; }
    public boolean suicideIntent() { return suicideIntent; }
    public boolean selfHarm() { return selfHarm; }
    public boolean harmToOthers() { return harmToOthers; }
    public String riskFactors() { return riskFactors; }
    public String protectiveFactors() { return protectiveFactors; }
    public String clinicalActions() { return clinicalActions; }
    public String observations() { return observations; }
    public LocalDateTime assessedAt() { return assessedAt; }
    public UUID assessedBy() { return assessedBy; }
}
