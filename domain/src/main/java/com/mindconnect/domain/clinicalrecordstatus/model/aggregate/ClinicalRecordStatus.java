package com.mindconnect.domain.clinicalrecordstatus.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.mindconnect.domain.common.model.AggregateRoot;
import com.mindconnect.domain.common.validation.DomainValidations;
import com.mindconnect.domain.clinicalrecordstatus.event.ClinicalRecordStatusRegisteredEvent;
import com.mindconnect.domain.clinicalrecordstatus.event.ClinicalRecordStatusUpdatedEvent;
import com.mindconnect.domain.clinicalrecordstatus.model.valueobject.ClinicalRecordStatusId;

/**
 * Agregado raíz del contexto clinicalrecordstatus: representa la tabla clinical_record_statuses.
 *
 * <p>El id y las fechas los pone el propio agregado. No usa Spring ni JPA.</p>
 */
public class ClinicalRecordStatus extends AggregateRoot {

    private final ClinicalRecordStatusId id;
    private String code;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private ClinicalRecordStatus(
            ClinicalRecordStatusId id,
            String code,
            String name,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        DomainValidations.required(code, "code");
        DomainValidations.required(name, "name");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(updatedAt, "updatedAt");
        this.code = code;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /** Crea un registro nuevo: genera el id y las fechas, y deja el evento de registro. */
    public static ClinicalRecordStatus register(
            String code,
            String name) {
        ClinicalRecordStatusId id = ClinicalRecordStatusId.generate();
        LocalDateTime now = LocalDateTime.now();
        ClinicalRecordStatus aggregate = new ClinicalRecordStatus(id, code, name, now, now);
        aggregate.recordEvent(new ClinicalRecordStatusRegisteredEvent(id, now));
        return aggregate;
    }

    /** Reconstruye un registro que ya existía (viene de la base de datos). No genera eventos. */
    public static ClinicalRecordStatus restore(
            ClinicalRecordStatusId id,
            String code,
            String name,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new ClinicalRecordStatus(id, code, name, createdAt, updatedAt);
    }

    /** Cambia los datos del registro, actualiza la fecha de modificación y deja el evento. */
    public void update(
            String code,
            String name) {
        DomainValidations.required(code, "code");
        DomainValidations.required(name, "name");
        this.code = code;
        this.name = name;
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new ClinicalRecordStatusUpdatedEvent(this.id, now));
    }

    public ClinicalRecordStatusId id() { return id; }
    public String code() { return code; }
    public String name() { return name; }
    public LocalDateTime createdAt() { return createdAt; }
    public LocalDateTime updatedAt() { return updatedAt; }
}
