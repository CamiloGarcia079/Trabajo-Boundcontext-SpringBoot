package com.mindconnect.domain.treatmentgoal.model.aggregate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import com.mindconnect.domain.common.model.AggregateRoot;
import com.mindconnect.domain.common.validation.DomainValidations;
import com.mindconnect.domain.treatmentgoal.event.TreatmentGoalRegisteredEvent;
import com.mindconnect.domain.treatmentgoal.event.TreatmentGoalUpdatedEvent;
import com.mindconnect.domain.treatmentgoal.model.valueobject.TreatmentGoalId;

/**
 * Agregado raíz del contexto treatmentgoal: representa la tabla treatment_goals.
 *
 * <p>Relaciones (se guardan solo por id, no como objetos):</p>
 * <ul>
 *   <li>treatmentPlanId -> TreatmentPlan</li>
 *   <li>treatmentGoalId -> TreatmentGoalStatus</li>
 * </ul>
 *
 * <p>El id y las fechas los pone el propio agregado. No usa Spring ni JPA.</p>
 */
public class TreatmentGoal extends AggregateRoot {

    private final TreatmentGoalId id;
    private UUID treatmentPlanId;
    private String description;
    private LocalDate targetDate;
    private LocalDateTime completedAt;
    private String notes;
    private UUID treatmentGoalId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private TreatmentGoal(
            TreatmentGoalId id,
            UUID treatmentPlanId,
            String description,
            LocalDate targetDate,
            LocalDateTime completedAt,
            String notes,
            UUID treatmentGoalId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        DomainValidations.required(treatmentPlanId, "treatmentPlanId");
        DomainValidations.required(description, "description");
        DomainValidations.required(targetDate, "targetDate");
        DomainValidations.required(completedAt, "completedAt");
        DomainValidations.required(notes, "notes");
        DomainValidations.required(treatmentGoalId, "treatmentGoalId");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(updatedAt, "updatedAt");
        this.treatmentPlanId = treatmentPlanId;
        this.description = description;
        this.targetDate = targetDate;
        this.completedAt = completedAt;
        this.notes = notes;
        this.treatmentGoalId = treatmentGoalId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /** Crea un registro nuevo: genera el id y las fechas, y deja el evento de registro. */
    public static TreatmentGoal register(
            UUID treatmentPlanId,
            String description,
            LocalDate targetDate,
            LocalDateTime completedAt,
            String notes,
            UUID treatmentGoalId) {
        TreatmentGoalId id = TreatmentGoalId.generate();
        LocalDateTime now = LocalDateTime.now();
        TreatmentGoal aggregate = new TreatmentGoal(id, treatmentPlanId, description, targetDate, completedAt, notes, treatmentGoalId, now, now);
        aggregate.recordEvent(new TreatmentGoalRegisteredEvent(id, now));
        return aggregate;
    }

    /** Reconstruye un registro que ya existía (viene de la base de datos). No genera eventos. */
    public static TreatmentGoal restore(
            TreatmentGoalId id,
            UUID treatmentPlanId,
            String description,
            LocalDate targetDate,
            LocalDateTime completedAt,
            String notes,
            UUID treatmentGoalId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new TreatmentGoal(id, treatmentPlanId, description, targetDate, completedAt, notes, treatmentGoalId, createdAt, updatedAt);
    }

    /** Cambia los datos del registro, actualiza la fecha de modificación y deja el evento. */
    public void update(
            UUID treatmentPlanId,
            String description,
            LocalDate targetDate,
            LocalDateTime completedAt,
            String notes,
            UUID treatmentGoalId) {
        DomainValidations.required(treatmentPlanId, "treatmentPlanId");
        DomainValidations.required(description, "description");
        DomainValidations.required(targetDate, "targetDate");
        DomainValidations.required(completedAt, "completedAt");
        DomainValidations.required(notes, "notes");
        DomainValidations.required(treatmentGoalId, "treatmentGoalId");
        this.treatmentPlanId = treatmentPlanId;
        this.description = description;
        this.targetDate = targetDate;
        this.completedAt = completedAt;
        this.notes = notes;
        this.treatmentGoalId = treatmentGoalId;
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new TreatmentGoalUpdatedEvent(this.id, now));
    }

    public TreatmentGoalId id() { return id; }
    public UUID treatmentPlanId() { return treatmentPlanId; }
    public String description() { return description; }
    public LocalDate targetDate() { return targetDate; }
    public LocalDateTime completedAt() { return completedAt; }
    public String notes() { return notes; }
    public UUID treatmentGoalId() { return treatmentGoalId; }
    public LocalDateTime createdAt() { return createdAt; }
    public LocalDateTime updatedAt() { return updatedAt; }
}
