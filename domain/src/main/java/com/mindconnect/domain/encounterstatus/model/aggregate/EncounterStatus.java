package com.mindconnect.domain.encounterstatus.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.mindconnect.domain.common.model.AggregateRoot;
import com.mindconnect.domain.common.validation.DomainValidations;
import com.mindconnect.domain.encounterstatus.event.EncounterStatusRegisteredEvent;
import com.mindconnect.domain.encounterstatus.event.EncounterStatusUpdatedEvent;
import com.mindconnect.domain.encounterstatus.model.valueobject.EncounterStatusId;

/**
 * Agregado raíz del contexto encounterstatus: representa la tabla encounter_statuses.
 *
 * <p>El id y las fechas los pone el propio agregado. No usa Spring ni JPA.</p>
 */
public class EncounterStatus extends AggregateRoot {

    private final EncounterStatusId id;
    private String code;
    private String name;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private EncounterStatus(
            EncounterStatusId id,
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
    public static EncounterStatus register(
            String code,
            String name) {
        EncounterStatusId id = EncounterStatusId.generate();
        LocalDateTime now = LocalDateTime.now();
        EncounterStatus aggregate = new EncounterStatus(id, code, name, true, now, now);
        aggregate.recordEvent(new EncounterStatusRegisteredEvent(id, now));
        return aggregate;
    }

    /** Reconstruye un registro que ya existía (viene de la base de datos). No genera eventos. */
    public static EncounterStatus restore(
            EncounterStatusId id,
            String code,
            String name,
            boolean active,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new EncounterStatus(id, code, name, active, createdAt, updatedAt);
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
        recordEvent(new EncounterStatusUpdatedEvent(this.id, now));
    }

    public EncounterStatusId id() { return id; }
    public String code() { return code; }
    public String name() { return name; }
    public boolean active() { return active; }
    public LocalDateTime createdAt() { return createdAt; }
    public LocalDateTime updatedAt() { return updatedAt; }
}
