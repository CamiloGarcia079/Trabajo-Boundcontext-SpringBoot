package com.mindconnect.domain.professionaltype.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.mindconnect.domain.common.model.AggregateRoot;
import com.mindconnect.domain.common.validation.DomainValidations;
import com.mindconnect.domain.professionaltype.event.ProfessionalTypeRegisteredEvent;
import com.mindconnect.domain.professionaltype.event.ProfessionalTypeUpdatedEvent;
import com.mindconnect.domain.professionaltype.model.valueobject.ProfessionalTypeId;

/**
 * Agregado raíz del contexto professionaltype: representa la tabla professional_types.
 *
 * <p>El id y las fechas los pone el propio agregado. No usa Spring ni JPA.</p>
 */
public class ProfessionalType extends AggregateRoot {

    private final ProfessionalTypeId id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private ProfessionalType(
            ProfessionalTypeId id,
            String name,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        DomainValidations.required(name, "name");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(updatedAt, "updatedAt");
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /** Crea un registro nuevo: genera el id y las fechas, y deja el evento de registro. */
    public static ProfessionalType register(
            String name) {
        ProfessionalTypeId id = ProfessionalTypeId.generate();
        LocalDateTime now = LocalDateTime.now();
        ProfessionalType aggregate = new ProfessionalType(id, name, now, now);
        aggregate.recordEvent(new ProfessionalTypeRegisteredEvent(id, now));
        return aggregate;
    }

    /** Reconstruye un registro que ya existía (viene de la base de datos). No genera eventos. */
    public static ProfessionalType restore(
            ProfessionalTypeId id,
            String name,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new ProfessionalType(id, name, createdAt, updatedAt);
    }

    /** Cambia los datos del registro, actualiza la fecha de modificación y deja el evento. */
    public void update(
            String name) {
        DomainValidations.required(name, "name");
        this.name = name;
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new ProfessionalTypeUpdatedEvent(this.id, now));
    }

    public ProfessionalTypeId id() { return id; }
    public String name() { return name; }
    public LocalDateTime createdAt() { return createdAt; }
    public LocalDateTime updatedAt() { return updatedAt; }
}
