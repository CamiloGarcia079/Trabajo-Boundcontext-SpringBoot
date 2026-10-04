package com.mindconnect.domain.patientallergy.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import com.mindconnect.domain.common.model.AggregateRoot;
import com.mindconnect.domain.common.validation.DomainValidations;
import com.mindconnect.domain.patientallergy.event.PatientAllergyRegisteredEvent;
import com.mindconnect.domain.patientallergy.event.PatientAllergyUpdatedEvent;
import com.mindconnect.domain.patientallergy.model.valueobject.PatientAllergyId;

/**
 * Agregado raíz del contexto patientallergy: representa la tabla patient_allergies.
 *
 * <p>Relaciones (se guardan solo por id, no como objetos):</p>
 * <ul>
 *   <li>patientId -> Patient</li>
 *   <li>recordedBy -> Professional</li>
 * </ul>
 *
 * <p>El id y las fechas los pone el propio agregado. No usa Spring ni JPA.</p>
 */
public class PatientAllergy extends AggregateRoot {

    private final PatientAllergyId id;
    private UUID patientId;
    private String substance;
    private String reaction;
    private String severity;
    private boolean active;
    private LocalDateTime recordedAt;
    private UUID recordedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private PatientAllergy(
            PatientAllergyId id,
            UUID patientId,
            String substance,
            String reaction,
            String severity,
            boolean active,
            LocalDateTime recordedAt,
            UUID recordedBy,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        DomainValidations.required(patientId, "patientId");
        DomainValidations.required(substance, "substance");
        DomainValidations.required(severity, "severity");
        DomainValidations.required(recordedAt, "recordedAt");
        DomainValidations.required(recordedBy, "recordedBy");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(updatedAt, "updatedAt");
        this.patientId = patientId;
        this.substance = substance;
        this.reaction = reaction;
        this.severity = severity;
        this.active = active;
        this.recordedAt = recordedAt;
        this.recordedBy = recordedBy;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /** Crea un registro nuevo: genera el id y las fechas, y deja el evento de registro. */
    public static PatientAllergy register(
            UUID patientId,
            String substance,
            String reaction,
            String severity,
            LocalDateTime recordedAt,
            UUID recordedBy) {
        PatientAllergyId id = PatientAllergyId.generate();
        LocalDateTime now = LocalDateTime.now();
        PatientAllergy aggregate = new PatientAllergy(id, patientId, substance, reaction, severity, true, recordedAt, recordedBy, now, now);
        aggregate.recordEvent(new PatientAllergyRegisteredEvent(id, now));
        return aggregate;
    }

    /** Reconstruye un registro que ya existía (viene de la base de datos). No genera eventos. */
    public static PatientAllergy restore(
            PatientAllergyId id,
            UUID patientId,
            String substance,
            String reaction,
            String severity,
            boolean active,
            LocalDateTime recordedAt,
            UUID recordedBy,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new PatientAllergy(id, patientId, substance, reaction, severity, active, recordedAt, recordedBy, createdAt, updatedAt);
    }

    /** Cambia los datos del registro, actualiza la fecha de modificación y deja el evento. */
    public void update(
            UUID patientId,
            String substance,
            String reaction,
            String severity,
            LocalDateTime recordedAt,
            UUID recordedBy) {
        DomainValidations.required(patientId, "patientId");
        DomainValidations.required(substance, "substance");
        DomainValidations.required(severity, "severity");
        DomainValidations.required(recordedAt, "recordedAt");
        DomainValidations.required(recordedBy, "recordedBy");
        this.patientId = patientId;
        this.substance = substance;
        this.reaction = reaction;
        this.severity = severity;
        this.recordedAt = recordedAt;
        this.recordedBy = recordedBy;
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new PatientAllergyUpdatedEvent(this.id, now));
    }

    public PatientAllergyId id() { return id; }
    public UUID patientId() { return patientId; }
    public String substance() { return substance; }
    public String reaction() { return reaction; }
    public String severity() { return severity; }
    public boolean active() { return active; }
    public LocalDateTime recordedAt() { return recordedAt; }
    public UUID recordedBy() { return recordedBy; }
    public LocalDateTime createdAt() { return createdAt; }
    public LocalDateTime updatedAt() { return updatedAt; }
}
