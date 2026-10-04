package com.mindconnect.domain.clinicalrecord.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import com.mindconnect.domain.common.model.AggregateRoot;
import com.mindconnect.domain.common.validation.DomainValidations;
import com.mindconnect.domain.clinicalrecord.event.ClinicalRecordRegisteredEvent;
import com.mindconnect.domain.clinicalrecord.event.ClinicalRecordUpdatedEvent;
import com.mindconnect.domain.clinicalrecord.model.valueobject.ClinicalRecordId;

/**
 * Agregado raíz del contexto clinicalrecord: representa la tabla clinical_records.
 *
 * <p>Relaciones (se guardan solo por id, no como objetos):</p>
 * <ul>
 *   <li>patientId -> Patient</li>
 *   <li>statusId -> ClinicalRecordStatus</li>
 *   <li>createdBy -> Professional</li>
 * </ul>
 *
 * <p>El id y las fechas los pone el propio agregado. No usa Spring ni JPA.</p>
 */
public class ClinicalRecord extends AggregateRoot {

    private final ClinicalRecordId id;
    private UUID patientId;
    private LocalDateTime creationDate;
    private String recordNumber;
    private LocalDateTime openedAt;
    private LocalDateTime closedAt;
    private UUID statusId;
    private LocalDateTime createdAt;
    private UUID createdBy;

    private ClinicalRecord(
            ClinicalRecordId id,
            UUID patientId,
            LocalDateTime creationDate,
            String recordNumber,
            LocalDateTime openedAt,
            LocalDateTime closedAt,
            UUID statusId,
            LocalDateTime createdAt,
            UUID createdBy) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        DomainValidations.required(patientId, "patientId");
        DomainValidations.required(creationDate, "creationDate");
        DomainValidations.required(recordNumber, "recordNumber");
        DomainValidations.required(openedAt, "openedAt");
        DomainValidations.required(closedAt, "closedAt");
        DomainValidations.required(statusId, "statusId");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(createdBy, "createdBy");
        this.patientId = patientId;
        this.creationDate = creationDate;
        this.recordNumber = recordNumber;
        this.openedAt = openedAt;
        this.closedAt = closedAt;
        this.statusId = statusId;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }

    /** Crea un registro nuevo: genera el id y las fechas, y deja el evento de registro. */
    public static ClinicalRecord register(
            UUID patientId,
            LocalDateTime creationDate,
            String recordNumber,
            LocalDateTime openedAt,
            LocalDateTime closedAt,
            UUID statusId,
            UUID createdBy) {
        ClinicalRecordId id = ClinicalRecordId.generate();
        LocalDateTime now = LocalDateTime.now();
        ClinicalRecord aggregate = new ClinicalRecord(id, patientId, creationDate, recordNumber, openedAt, closedAt, statusId, now, createdBy);
        aggregate.recordEvent(new ClinicalRecordRegisteredEvent(id, now));
        return aggregate;
    }

    /** Reconstruye un registro que ya existía (viene de la base de datos). No genera eventos. */
    public static ClinicalRecord restore(
            ClinicalRecordId id,
            UUID patientId,
            LocalDateTime creationDate,
            String recordNumber,
            LocalDateTime openedAt,
            LocalDateTime closedAt,
            UUID statusId,
            LocalDateTime createdAt,
            UUID createdBy) {
        return new ClinicalRecord(id, patientId, creationDate, recordNumber, openedAt, closedAt, statusId, createdAt, createdBy);
    }

    /** Cambia los datos del registro, actualiza la fecha de modificación y deja el evento. */
    public void update(
            UUID patientId,
            LocalDateTime creationDate,
            String recordNumber,
            LocalDateTime openedAt,
            LocalDateTime closedAt,
            UUID statusId) {
        DomainValidations.required(patientId, "patientId");
        DomainValidations.required(creationDate, "creationDate");
        DomainValidations.required(recordNumber, "recordNumber");
        DomainValidations.required(openedAt, "openedAt");
        DomainValidations.required(closedAt, "closedAt");
        DomainValidations.required(statusId, "statusId");
        this.patientId = patientId;
        this.creationDate = creationDate;
        this.recordNumber = recordNumber;
        this.openedAt = openedAt;
        this.closedAt = closedAt;
        this.statusId = statusId;
        LocalDateTime now = LocalDateTime.now();
        recordEvent(new ClinicalRecordUpdatedEvent(this.id, now));
    }

    public ClinicalRecordId id() { return id; }
    public UUID patientId() { return patientId; }
    public LocalDateTime creationDate() { return creationDate; }
    public String recordNumber() { return recordNumber; }
    public LocalDateTime openedAt() { return openedAt; }
    public LocalDateTime closedAt() { return closedAt; }
    public UUID statusId() { return statusId; }
    public LocalDateTime createdAt() { return createdAt; }
    public UUID createdBy() { return createdBy; }
}
