package com.mindconnect.domain.clinicalnote.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import com.mindconnect.domain.common.model.AggregateRoot;
import com.mindconnect.domain.common.validation.DomainValidations;
import com.mindconnect.domain.clinicalnote.event.ClinicalNoteRegisteredEvent;
import com.mindconnect.domain.clinicalnote.event.ClinicalNoteUpdatedEvent;
import com.mindconnect.domain.clinicalnote.model.valueobject.ClinicalNoteId;

/**
 * Agregado raíz del contexto clinicalnote: representa la tabla clinical_notes.
 *
 * <p>Relaciones (se guardan solo por id, no como objetos):</p>
 * <ul>
 *   <li>encounterId -> Encounter</li>
 *   <li>professionalId -> Professional</li>
 * </ul>
 *
 * <p>El id y las fechas los pone el propio agregado. No usa Spring ni JPA.</p>
 */
public class ClinicalNote extends AggregateRoot {

    private final ClinicalNoteId id;
    private UUID encounterId;
    private UUID professionalId;
    private String subjective;
    private String objective;
    private String assessment;
    private String plan;
    private String additionalNotes;
    private LocalDateTime signedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private ClinicalNote(
            ClinicalNoteId id,
            UUID encounterId,
            UUID professionalId,
            String subjective,
            String objective,
            String assessment,
            String plan,
            String additionalNotes,
            LocalDateTime signedAt,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        DomainValidations.required(encounterId, "encounterId");
        DomainValidations.required(professionalId, "professionalId");
        DomainValidations.required(subjective, "subjective");
        DomainValidations.required(objective, "objective");
        DomainValidations.required(assessment, "assessment");
        DomainValidations.required(plan, "plan");
        DomainValidations.required(additionalNotes, "additionalNotes");
        DomainValidations.required(signedAt, "signedAt");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(updatedAt, "updatedAt");
        this.encounterId = encounterId;
        this.professionalId = professionalId;
        this.subjective = subjective;
        this.objective = objective;
        this.assessment = assessment;
        this.plan = plan;
        this.additionalNotes = additionalNotes;
        this.signedAt = signedAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /** Crea un registro nuevo: genera el id y las fechas, y deja el evento de registro. */
    public static ClinicalNote register(
            UUID encounterId,
            UUID professionalId,
            String subjective,
            String objective,
            String assessment,
            String plan,
            String additionalNotes,
            LocalDateTime signedAt) {
        ClinicalNoteId id = ClinicalNoteId.generate();
        LocalDateTime now = LocalDateTime.now();
        ClinicalNote aggregate = new ClinicalNote(id, encounterId, professionalId, subjective, objective, assessment, plan, additionalNotes, signedAt, now, now);
        aggregate.recordEvent(new ClinicalNoteRegisteredEvent(id, now));
        return aggregate;
    }

    /** Reconstruye un registro que ya existía (viene de la base de datos). No genera eventos. */
    public static ClinicalNote restore(
            ClinicalNoteId id,
            UUID encounterId,
            UUID professionalId,
            String subjective,
            String objective,
            String assessment,
            String plan,
            String additionalNotes,
            LocalDateTime signedAt,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new ClinicalNote(id, encounterId, professionalId, subjective, objective, assessment, plan, additionalNotes, signedAt, createdAt, updatedAt);
    }

    /** Cambia los datos del registro, actualiza la fecha de modificación y deja el evento. */
    public void update(
            UUID encounterId,
            UUID professionalId,
            String subjective,
            String objective,
            String assessment,
            String plan,
            String additionalNotes,
            LocalDateTime signedAt) {
        DomainValidations.required(encounterId, "encounterId");
        DomainValidations.required(professionalId, "professionalId");
        DomainValidations.required(subjective, "subjective");
        DomainValidations.required(objective, "objective");
        DomainValidations.required(assessment, "assessment");
        DomainValidations.required(plan, "plan");
        DomainValidations.required(additionalNotes, "additionalNotes");
        DomainValidations.required(signedAt, "signedAt");
        this.encounterId = encounterId;
        this.professionalId = professionalId;
        this.subjective = subjective;
        this.objective = objective;
        this.assessment = assessment;
        this.plan = plan;
        this.additionalNotes = additionalNotes;
        this.signedAt = signedAt;
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new ClinicalNoteUpdatedEvent(this.id, now));
    }

    public ClinicalNoteId id() { return id; }
    public UUID encounterId() { return encounterId; }
    public UUID professionalId() { return professionalId; }
    public String subjective() { return subjective; }
    public String objective() { return objective; }
    public String assessment() { return assessment; }
    public String plan() { return plan; }
    public String additionalNotes() { return additionalNotes; }
    public LocalDateTime signedAt() { return signedAt; }
    public LocalDateTime createdAt() { return createdAt; }
    public LocalDateTime updatedAt() { return updatedAt; }
}
