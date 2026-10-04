package com.mindconnect.domain.study.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.mindconnect.domain.common.model.AggregateRoot;
import com.mindconnect.domain.common.validation.DomainValidations;
import com.mindconnect.domain.study.event.StudyRegisteredEvent;
import com.mindconnect.domain.study.event.StudyUpdatedEvent;
import com.mindconnect.domain.study.model.valueobject.StudyId;

/**
 * Agregado raíz del contexto study: representa la tabla studies.
 *
 * <p>El id y las fechas los pone el propio agregado. No usa Spring ni JPA.</p>
 */
public class Study extends AggregateRoot {

    private final StudyId id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Study(
            StudyId id,
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
    public static Study register(
            String name) {
        StudyId id = StudyId.generate();
        LocalDateTime now = LocalDateTime.now();
        Study aggregate = new Study(id, name, now, now);
        aggregate.recordEvent(new StudyRegisteredEvent(id, now));
        return aggregate;
    }

    /** Reconstruye un registro que ya existía (viene de la base de datos). No genera eventos. */
    public static Study restore(
            StudyId id,
            String name,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new Study(id, name, createdAt, updatedAt);
    }

    /** Cambia los datos del registro, actualiza la fecha de modificación y deja el evento. */
    public void update(
            String name) {
        DomainValidations.required(name, "name");
        this.name = name;
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new StudyUpdatedEvent(this.id, now));
    }

    public StudyId id() { return id; }
    public String name() { return name; }
    public LocalDateTime createdAt() { return createdAt; }
    public LocalDateTime updatedAt() { return updatedAt; }
}
