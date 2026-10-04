package com.mindconnect.domain.encounter.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import com.mindconnect.domain.common.model.AggregateRoot;
import com.mindconnect.domain.common.validation.DomainValidations;
import com.mindconnect.domain.encounter.event.EncounterRegisteredEvent;
import com.mindconnect.domain.encounter.event.EncounterUpdatedEvent;
import com.mindconnect.domain.encounter.model.valueobject.EncounterId;

/**
 * Agregado raíz del contexto encounter: representa la tabla encounters.
 *
 * <p>Relaciones (se guardan solo por id, no como objetos):</p>
 * <ul>
 *   <li>clinicalRecordId -> ClinicalRecord</li>
 *   <li>professionalId -> Professional</li>
 *   <li>encounterTypeId -> EncounterType</li>
 *   <li>modalityId -> EncounterModality</li>
 *   <li>statusId -> EncounterStatus</li>
 *   <li>createdBy -> Professional</li>
 *   <li>updatedBy -> Professional</li>
 * </ul>
 *
 * <p>El id y las fechas los pone el propio agregado. No usa Spring ni JPA.</p>
 */
public class Encounter extends AggregateRoot {

    private final EncounterId id;
    private UUID clinicalRecordId;
    private UUID professionalId;
    private UUID encounterTypeId;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private String reasonForVisit;
    private String currentCondition;
    private UUID modalityId;
    private UUID statusId;
    private LocalDateTime createdAt;
    private UUID createdBy;
    private LocalDateTime updatedAt;
    private UUID updatedBy;

    private Encounter(
            EncounterId id,
            UUID clinicalRecordId,
            UUID professionalId,
            UUID encounterTypeId,
            LocalDateTime startedAt,
            LocalDateTime endedAt,
            String reasonForVisit,
            String currentCondition,
            UUID modalityId,
            UUID statusId,
            LocalDateTime createdAt,
            UUID createdBy,
            LocalDateTime updatedAt,
            UUID updatedBy) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        DomainValidations.required(clinicalRecordId, "clinicalRecordId");
        DomainValidations.required(professionalId, "professionalId");
        DomainValidations.required(encounterTypeId, "encounterTypeId");
        DomainValidations.required(startedAt, "startedAt");
        DomainValidations.required(endedAt, "endedAt");
        DomainValidations.required(reasonForVisit, "reasonForVisit");
        DomainValidations.required(currentCondition, "currentCondition");
        DomainValidations.required(modalityId, "modalityId");
        DomainValidations.required(statusId, "statusId");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(createdBy, "createdBy");
        DomainValidations.required(updatedAt, "updatedAt");
        DomainValidations.required(updatedBy, "updatedBy");
        this.clinicalRecordId = clinicalRecordId;
        this.professionalId = professionalId;
        this.encounterTypeId = encounterTypeId;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.reasonForVisit = reasonForVisit;
        this.currentCondition = currentCondition;
        this.modalityId = modalityId;
        this.statusId = statusId;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
    }

    /** Crea un registro nuevo: genera el id y las fechas, y deja el evento de registro. */
    public static Encounter register(
            UUID clinicalRecordId,
            UUID professionalId,
            UUID encounterTypeId,
            LocalDateTime startedAt,
            LocalDateTime endedAt,
            String reasonForVisit,
            String currentCondition,
            UUID modalityId,
            UUID statusId,
            UUID createdBy,
            UUID updatedBy) {
        EncounterId id = EncounterId.generate();
        LocalDateTime now = LocalDateTime.now();
        Encounter aggregate = new Encounter(id, clinicalRecordId, professionalId, encounterTypeId, startedAt, endedAt, reasonForVisit, currentCondition, modalityId, statusId, now, createdBy, now, updatedBy);
        aggregate.recordEvent(new EncounterRegisteredEvent(id, now));
        return aggregate;
    }

    /** Reconstruye un registro que ya existía (viene de la base de datos). No genera eventos. */
    public static Encounter restore(
            EncounterId id,
            UUID clinicalRecordId,
            UUID professionalId,
            UUID encounterTypeId,
            LocalDateTime startedAt,
            LocalDateTime endedAt,
            String reasonForVisit,
            String currentCondition,
            UUID modalityId,
            UUID statusId,
            LocalDateTime createdAt,
            UUID createdBy,
            LocalDateTime updatedAt,
            UUID updatedBy) {
        return new Encounter(id, clinicalRecordId, professionalId, encounterTypeId, startedAt, endedAt, reasonForVisit, currentCondition, modalityId, statusId, createdAt, createdBy, updatedAt, updatedBy);
    }

    /** Cambia los datos del registro, actualiza la fecha de modificación y deja el evento. */
    public void update(
            UUID clinicalRecordId,
            UUID professionalId,
            UUID encounterTypeId,
            LocalDateTime startedAt,
            LocalDateTime endedAt,
            String reasonForVisit,
            String currentCondition,
            UUID modalityId,
            UUID statusId,
            UUID updatedBy) {
        DomainValidations.required(clinicalRecordId, "clinicalRecordId");
        DomainValidations.required(professionalId, "professionalId");
        DomainValidations.required(encounterTypeId, "encounterTypeId");
        DomainValidations.required(startedAt, "startedAt");
        DomainValidations.required(endedAt, "endedAt");
        DomainValidations.required(reasonForVisit, "reasonForVisit");
        DomainValidations.required(currentCondition, "currentCondition");
        DomainValidations.required(modalityId, "modalityId");
        DomainValidations.required(statusId, "statusId");
        DomainValidations.required(updatedBy, "updatedBy");
        this.clinicalRecordId = clinicalRecordId;
        this.professionalId = professionalId;
        this.encounterTypeId = encounterTypeId;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.reasonForVisit = reasonForVisit;
        this.currentCondition = currentCondition;
        this.modalityId = modalityId;
        this.statusId = statusId;
        this.updatedBy = updatedBy;
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new EncounterUpdatedEvent(this.id, now));
    }

    public EncounterId id() { return id; }
    public UUID clinicalRecordId() { return clinicalRecordId; }
    public UUID professionalId() { return professionalId; }
    public UUID encounterTypeId() { return encounterTypeId; }
    public LocalDateTime startedAt() { return startedAt; }
    public LocalDateTime endedAt() { return endedAt; }
    public String reasonForVisit() { return reasonForVisit; }
    public String currentCondition() { return currentCondition; }
    public UUID modalityId() { return modalityId; }
    public UUID statusId() { return statusId; }
    public LocalDateTime createdAt() { return createdAt; }
    public UUID createdBy() { return createdBy; }
    public LocalDateTime updatedAt() { return updatedAt; }
    public UUID updatedBy() { return updatedBy; }
}
