package com.mindconnect.domain.assessmenttype.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.mindconnect.domain.common.model.AggregateRoot;
import com.mindconnect.domain.common.validation.DomainValidations;
import com.mindconnect.domain.assessmenttype.event.AssessmentTypeRegisteredEvent;
import com.mindconnect.domain.assessmenttype.event.AssessmentTypeUpdatedEvent;
import com.mindconnect.domain.assessmenttype.model.valueobject.AssessmentTypeId;

/**
 * Agregado raíz del contexto assessmenttype: representa la tabla assessment_types.
 *
 * <p>El id y las fechas los pone el propio agregado. No usa Spring ni JPA.</p>
 */
public class AssessmentType extends AggregateRoot {

    private final AssessmentTypeId id;
    private String code;
    private String name;
    private boolean active;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private AssessmentType(
            AssessmentTypeId id,
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
    public static AssessmentType register(
            String code,
            String name,
            String description) {
        AssessmentTypeId id = AssessmentTypeId.generate();
        LocalDateTime now = LocalDateTime.now();
        AssessmentType aggregate = new AssessmentType(id, code, name, true, description, now, now);
        aggregate.recordEvent(new AssessmentTypeRegisteredEvent(id, now));
        return aggregate;
    }

    /** Reconstruye un registro que ya existía (viene de la base de datos). No genera eventos. */
    public static AssessmentType restore(
            AssessmentTypeId id,
            String code,
            String name,
            boolean active,
            String description,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new AssessmentType(id, code, name, active, description, createdAt, updatedAt);
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
        recordEvent(new AssessmentTypeUpdatedEvent(this.id, now));
    }

    public AssessmentTypeId id() { return id; }
    public String code() { return code; }
    public String name() { return name; }
    public boolean active() { return active; }
    public String description() { return description; }
    public LocalDateTime createdAt() { return createdAt; }
    public LocalDateTime updatedAt() { return updatedAt; }
}
