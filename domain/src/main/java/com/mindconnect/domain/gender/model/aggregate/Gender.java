package com.mindconnect.domain.gender.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.mindconnect.domain.common.model.AggregateRoot;
import com.mindconnect.domain.common.validation.DomainValidations;
import com.mindconnect.domain.gender.event.GenderRegisteredEvent;
import com.mindconnect.domain.gender.event.GenderUpdatedEvent;
import com.mindconnect.domain.gender.model.valueobject.GenderId;

/**
 * Agregado raíz del contexto gender: representa la tabla genders.
 *
 * <p>El id y las fechas los pone el propio agregado. No usa Spring ni JPA.</p>
 */
public class Gender extends AggregateRoot {

    private final GenderId id;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Gender(
            GenderId id,
            String description,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        DomainValidations.required(description, "description");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(updatedAt, "updatedAt");
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /** Crea un registro nuevo: genera el id y las fechas, y deja el evento de registro. */
    public static Gender register(
            String description) {
        GenderId id = GenderId.generate();
        LocalDateTime now = LocalDateTime.now();
        Gender aggregate = new Gender(id, description, now, now);
        aggregate.recordEvent(new GenderRegisteredEvent(id, now));
        return aggregate;
    }

    /** Reconstruye un registro que ya existía (viene de la base de datos). No genera eventos. */
    public static Gender restore(
            GenderId id,
            String description,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new Gender(id, description, createdAt, updatedAt);
    }

    /** Cambia los datos del registro, actualiza la fecha de modificación y deja el evento. */
    public void update(
            String description) {
        DomainValidations.required(description, "description");
        this.description = description;
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new GenderUpdatedEvent(this.id, now));
    }

    public GenderId id() { return id; }
    public String description() { return description; }
    public LocalDateTime createdAt() { return createdAt; }
    public LocalDateTime updatedAt() { return updatedAt; }
}
