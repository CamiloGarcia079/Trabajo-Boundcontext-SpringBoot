package com.mindconnect.domain.airunstatus.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.mindconnect.domain.common.model.AggregateRoot;
import com.mindconnect.domain.common.validation.DomainValidations;
import com.mindconnect.domain.airunstatus.event.AiRunStatusRegisteredEvent;
import com.mindconnect.domain.airunstatus.event.AiRunStatusUpdatedEvent;
import com.mindconnect.domain.airunstatus.model.valueobject.AiRunStatusId;

/**
 * Agregado raíz del contexto airunstatus: representa la tabla ai_runs_statuses.
 *
 * <p>El id y las fechas los pone el propio agregado. No usa Spring ni JPA.</p>
 */
public class AiRunStatus extends AggregateRoot {

    private final AiRunStatusId id;
    private String nameStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private AiRunStatus(
            AiRunStatusId id,
            String nameStatus,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        DomainValidations.required(nameStatus, "nameStatus");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(updatedAt, "updatedAt");
        this.nameStatus = nameStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /** Crea un registro nuevo: genera el id y las fechas, y deja el evento de registro. */
    public static AiRunStatus register(
            String nameStatus) {
        AiRunStatusId id = AiRunStatusId.generate();
        LocalDateTime now = LocalDateTime.now();
        AiRunStatus aggregate = new AiRunStatus(id, nameStatus, now, now);
        aggregate.recordEvent(new AiRunStatusRegisteredEvent(id, now));
        return aggregate;
    }

    /** Reconstruye un registro que ya existía (viene de la base de datos). No genera eventos. */
    public static AiRunStatus restore(
            AiRunStatusId id,
            String nameStatus,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new AiRunStatus(id, nameStatus, createdAt, updatedAt);
    }

    /** Cambia los datos del registro, actualiza la fecha de modificación y deja el evento. */
    public void update(
            String nameStatus) {
        DomainValidations.required(nameStatus, "nameStatus");
        this.nameStatus = nameStatus;
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new AiRunStatusUpdatedEvent(this.id, now));
    }

    public AiRunStatusId id() { return id; }
    public String nameStatus() { return nameStatus; }
    public LocalDateTime createdAt() { return createdAt; }
    public LocalDateTime updatedAt() { return updatedAt; }
}
