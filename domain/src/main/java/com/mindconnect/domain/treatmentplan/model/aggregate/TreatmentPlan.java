package com.mindconnect.domain.treatmentplan.model.aggregate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import com.mindconnect.domain.common.model.AggregateRoot;
import com.mindconnect.domain.common.validation.DomainValidations;
import com.mindconnect.domain.treatmentplan.event.TreatmentPlanRegisteredEvent;
import com.mindconnect.domain.treatmentplan.event.TreatmentPlanUpdatedEvent;
import com.mindconnect.domain.treatmentplan.model.valueobject.TreatmentPlanId;

/**
 * Agregado raíz del contexto treatmentplan: representa la tabla treatment_plans.
 *
 * <p>Relaciones (se guardan solo por id, no como objetos):</p>
 * <ul>
 *   <li>encounterId -> Encounter</li>
 *   <li>professionalId -> Professional</li>
 *   <li>treatmentStatusId -> TreatmentStatus</li>
 * </ul>
 *
 * <p>El id y las fechas los pone el propio agregado. No usa Spring ni JPA.</p>
 */
public class TreatmentPlan extends AggregateRoot {

    private final TreatmentPlanId id;
    private UUID encounterId;
    private UUID professionalId;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private UUID treatmentStatusId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private TreatmentPlan(
            TreatmentPlanId id,
            UUID encounterId,
            UUID professionalId,
            String title,
            String description,
            LocalDate startDate,
            LocalDate endDate,
            UUID treatmentStatusId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        DomainValidations.required(encounterId, "encounterId");
        DomainValidations.required(professionalId, "professionalId");
        DomainValidations.required(title, "title");
        DomainValidations.required(description, "description");
        DomainValidations.required(startDate, "startDate");
        DomainValidations.required(endDate, "endDate");
        DomainValidations.required(treatmentStatusId, "treatmentStatusId");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(updatedAt, "updatedAt");
        this.encounterId = encounterId;
        this.professionalId = professionalId;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.treatmentStatusId = treatmentStatusId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /** Crea un registro nuevo: genera el id y las fechas, y deja el evento de registro. */
    public static TreatmentPlan register(
            UUID encounterId,
            UUID professionalId,
            String title,
            String description,
            LocalDate startDate,
            LocalDate endDate,
            UUID treatmentStatusId) {
        TreatmentPlanId id = TreatmentPlanId.generate();
        LocalDateTime now = LocalDateTime.now();
        TreatmentPlan aggregate = new TreatmentPlan(id, encounterId, professionalId, title, description, startDate, endDate, treatmentStatusId, now, now);
        aggregate.recordEvent(new TreatmentPlanRegisteredEvent(id, now));
        return aggregate;
    }

    /** Reconstruye un registro que ya existía (viene de la base de datos). No genera eventos. */
    public static TreatmentPlan restore(
            TreatmentPlanId id,
            UUID encounterId,
            UUID professionalId,
            String title,
            String description,
            LocalDate startDate,
            LocalDate endDate,
            UUID treatmentStatusId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new TreatmentPlan(id, encounterId, professionalId, title, description, startDate, endDate, treatmentStatusId, createdAt, updatedAt);
    }

    /** Cambia los datos del registro, actualiza la fecha de modificación y deja el evento. */
    public void update(
            UUID encounterId,
            UUID professionalId,
            String title,
            String description,
            LocalDate startDate,
            LocalDate endDate,
            UUID treatmentStatusId) {
        DomainValidations.required(encounterId, "encounterId");
        DomainValidations.required(professionalId, "professionalId");
        DomainValidations.required(title, "title");
        DomainValidations.required(description, "description");
        DomainValidations.required(startDate, "startDate");
        DomainValidations.required(endDate, "endDate");
        DomainValidations.required(treatmentStatusId, "treatmentStatusId");
        this.encounterId = encounterId;
        this.professionalId = professionalId;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.treatmentStatusId = treatmentStatusId;
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new TreatmentPlanUpdatedEvent(this.id, now));
    }

    public TreatmentPlanId id() { return id; }
    public UUID encounterId() { return encounterId; }
    public UUID professionalId() { return professionalId; }
    public String title() { return title; }
    public String description() { return description; }
    public LocalDate startDate() { return startDate; }
    public LocalDate endDate() { return endDate; }
    public UUID treatmentStatusId() { return treatmentStatusId; }
    public LocalDateTime createdAt() { return createdAt; }
    public LocalDateTime updatedAt() { return updatedAt; }
}
