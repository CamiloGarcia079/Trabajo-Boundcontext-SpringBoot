package com.mindconnect.domain.consenttype.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.mindconnect.domain.common.model.AggregateRoot;
import com.mindconnect.domain.common.validation.DomainValidations;
import com.mindconnect.domain.consenttype.event.ConsentTypeRegisteredEvent;
import com.mindconnect.domain.consenttype.event.ConsentTypeUpdatedEvent;
import com.mindconnect.domain.consenttype.model.valueobject.ConsentTypeId;

/**
 * Agregado raíz del contexto consenttype: representa la tabla consent_types.
 *
 * <p>El id y las fechas los pone el propio agregado. No usa Spring ni JPA.</p>
 */
public class ConsentType extends AggregateRoot {

    private final ConsentTypeId id;
    private String code;
    private String name;
    private boolean active;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private ConsentType(
            ConsentTypeId id,
            String code,
            String name,
            boolean active,
            String description,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        DomainValidations.required(code, "code");
        DomainValidations.required(name, "name");
        DomainValidations.required(description, "description");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(updatedAt, "updatedAt");
        this.code = code;
        this.name = name;
        this.active = active;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /** Crea un registro nuevo: genera el id y las fechas, y deja el evento de registro. */
    public static ConsentType register(
            String code,
            String name,
            String description) {
        ConsentTypeId id = ConsentTypeId.generate();
        LocalDateTime now = LocalDateTime.now();
        ConsentType aggregate = new ConsentType(id, code, name, true, description, now, now);
        aggregate.recordEvent(new ConsentTypeRegisteredEvent(id, now));
        return aggregate;
    }

    /** Reconstruye un registro que ya existía (viene de la base de datos). No genera eventos. */
    public static ConsentType restore(
            ConsentTypeId id,
            String code,
            String name,
            boolean active,
            String description,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new ConsentType(id, code, name, active, description, createdAt, updatedAt);
    }

    /** Cambia los datos del registro, actualiza la fecha de modificación y deja el evento. */
    public void update(
            String code,
            String name,
            String description) {
        DomainValidations.required(code, "code");
        DomainValidations.required(name, "name");
        DomainValidations.required(description, "description");
        this.code = code;
        this.name = name;
        this.description = description;
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new ConsentTypeUpdatedEvent(this.id, now));
    }

    public ConsentTypeId id() { return id; }
    public String code() { return code; }
    public String name() { return name; }
    public boolean active() { return active; }
    public String description() { return description; }
    public LocalDateTime createdAt() { return createdAt; }
    public LocalDateTime updatedAt() { return updatedAt; }
}
