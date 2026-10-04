package com.mindconnect.domain.priority.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.mindconnect.domain.common.model.AggregateRoot;
import com.mindconnect.domain.common.validation.DomainValidations;
import com.mindconnect.domain.priority.event.PriorityRegisteredEvent;
import com.mindconnect.domain.priority.event.PriorityUpdatedEvent;
import com.mindconnect.domain.priority.model.valueobject.PriorityId;

/**
 * Agregado raíz del contexto priority: representa la tabla priorities.
 *
 * <p>El id y las fechas los pone el propio agregado. No usa Spring ni JPA.</p>
 */
public class Priority extends AggregateRoot {

    private final PriorityId id;
    private String namePriority;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Priority(
            PriorityId id,
            String namePriority,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        DomainValidations.required(namePriority, "namePriority");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(updatedAt, "updatedAt");
        this.namePriority = namePriority;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /** Crea un registro nuevo: genera el id y las fechas, y deja el evento de registro. */
    public static Priority register(
            String namePriority) {
        PriorityId id = PriorityId.generate();
        LocalDateTime now = LocalDateTime.now();
        Priority aggregate = new Priority(id, namePriority, now, now);
        aggregate.recordEvent(new PriorityRegisteredEvent(id, now));
        return aggregate;
    }

    /** Reconstruye un registro que ya existía (viene de la base de datos). No genera eventos. */
    public static Priority restore(
            PriorityId id,
            String namePriority,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new Priority(id, namePriority, createdAt, updatedAt);
    }

    /** Cambia los datos del registro, actualiza la fecha de modificación y deja el evento. */
    public void update(
            String namePriority) {
        DomainValidations.required(namePriority, "namePriority");
        this.namePriority = namePriority;
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new PriorityUpdatedEvent(this.id, now));
    }

    public PriorityId id() { return id; }
    public String namePriority() { return namePriority; }
    public LocalDateTime createdAt() { return createdAt; }
    public LocalDateTime updatedAt() { return updatedAt; }
}
