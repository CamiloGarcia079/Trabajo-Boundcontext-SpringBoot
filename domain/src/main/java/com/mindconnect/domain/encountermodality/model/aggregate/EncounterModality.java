package com.mindconnect.domain.encountermodality.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.mindconnect.domain.common.model.AggregateRoot;
import com.mindconnect.domain.common.validation.DomainValidations;
import com.mindconnect.domain.encountermodality.event.EncounterModalityRegisteredEvent;
import com.mindconnect.domain.encountermodality.event.EncounterModalityUpdatedEvent;
import com.mindconnect.domain.encountermodality.model.valueobject.EncounterModalityId;

/**
 * Agregado raíz del contexto encountermodality: representa la tabla encounter_modalities.
 *
 * <p>El id y las fechas los pone el propio agregado. No usa Spring ni JPA.</p>
 */
public class EncounterModality extends AggregateRoot {

    private final EncounterModalityId id;
    private String code;
    private String name;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private EncounterModality(
            EncounterModalityId id,
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
    public static EncounterModality register(
            String code,
            String name) {
        EncounterModalityId id = EncounterModalityId.generate();
        LocalDateTime now = LocalDateTime.now();
        EncounterModality aggregate = new EncounterModality(id, code, name, true, now, now);
        aggregate.recordEvent(new EncounterModalityRegisteredEvent(id, now));
        return aggregate;
    }

    /** Reconstruye un registro que ya existía (viene de la base de datos). No genera eventos. */
    public static EncounterModality restore(
            EncounterModalityId id,
            String code,
            String name,
            boolean active,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new EncounterModality(id, code, name, active, createdAt, updatedAt);
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
        recordEvent(new EncounterModalityUpdatedEvent(this.id, now));
    }

    public EncounterModalityId id() { return id; }
    public String code() { return code; }
    public String name() { return name; }
    public boolean active() { return active; }
    public LocalDateTime createdAt() { return createdAt; }
    public LocalDateTime updatedAt() { return updatedAt; }
}
