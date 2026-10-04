package com.mindconnect.domain.treatmentstatus.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.mindconnect.domain.common.model.AggregateRoot;
import com.mindconnect.domain.common.validation.DomainValidations;
import com.mindconnect.domain.treatmentstatus.event.TreatmentStatusRegisteredEvent;
import com.mindconnect.domain.treatmentstatus.event.TreatmentStatusUpdatedEvent;
import com.mindconnect.domain.treatmentstatus.model.valueobject.TreatmentStatusId;

/**
 * Agregado raíz del contexto treatmentstatus: representa la tabla treatment_statuses.
 *
 * <p>El id y las fechas los pone el propio agregado. No usa Spring ni JPA.</p>
 */
public class TreatmentStatus extends AggregateRoot {

    private final TreatmentStatusId id;
    private String code;
    private String name;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private TreatmentStatus(
            TreatmentStatusId id,
            String code,
            String name,
            boolean active,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        DomainValidations.required(code, "code");
        DomainValidations.required(name, "name");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(updatedAt, "updatedAt");
        this.code = code;
        this.name = name;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /** Crea un registro nuevo: genera el id y las fechas, y deja el evento de registro. */
    public static TreatmentStatus register(
            String code,
            String name) {
        TreatmentStatusId id = TreatmentStatusId.generate();
        LocalDateTime now = LocalDateTime.now();
        TreatmentStatus aggregate = new TreatmentStatus(id, code, name, true, now, now);
        aggregate.recordEvent(new TreatmentStatusRegisteredEvent(id, now));
        return aggregate;
    }

    /** Reconstruye un registro que ya existía (viene de la base de datos). No genera eventos. */
    public static TreatmentStatus restore(
            TreatmentStatusId id,
            String code,
            String name,
            boolean active,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new TreatmentStatus(id, code, name, active, createdAt, updatedAt);
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
        recordEvent(new TreatmentStatusUpdatedEvent(this.id, now));
    }

    public TreatmentStatusId id() { return id; }
    public String code() { return code; }
    public String name() { return name; }
    public boolean active() { return active; }
    public LocalDateTime createdAt() { return createdAt; }
    public LocalDateTime updatedAt() { return updatedAt; }
}
