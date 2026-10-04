package com.mindconnect.domain.escalationstatus.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.mindconnect.domain.common.model.AggregateRoot;
import com.mindconnect.domain.common.validation.DomainValidations;
import com.mindconnect.domain.escalationstatus.event.EscalationStatusRegisteredEvent;
import com.mindconnect.domain.escalationstatus.event.EscalationStatusUpdatedEvent;
import com.mindconnect.domain.escalationstatus.model.valueobject.EscalationStatusId;

/**
 * Agregado raíz del contexto escalationstatus: representa la tabla escalations_statuses.
 *
 * <p>El id y las fechas los pone el propio agregado. No usa Spring ni JPA.</p>
 */
public class EscalationStatus extends AggregateRoot {

    private final EscalationStatusId id;
    private String nameStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private EscalationStatus(
            EscalationStatusId id,
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
    public static EscalationStatus register(
            String nameStatus) {
        EscalationStatusId id = EscalationStatusId.generate();
        LocalDateTime now = LocalDateTime.now();
        EscalationStatus aggregate = new EscalationStatus(id, nameStatus, now, now);
        aggregate.recordEvent(new EscalationStatusRegisteredEvent(id, now));
        return aggregate;
    }

    /** Reconstruye un registro que ya existía (viene de la base de datos). No genera eventos. */
    public static EscalationStatus restore(
            EscalationStatusId id,
            String nameStatus,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new EscalationStatus(id, nameStatus, createdAt, updatedAt);
    }

    /** Cambia los datos del registro, actualiza la fecha de modificación y deja el evento. */
    public void update(
            String nameStatus) {
        DomainValidations.required(nameStatus, "nameStatus");
        this.nameStatus = nameStatus;
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new EscalationStatusUpdatedEvent(this.id, now));
    }

    public EscalationStatusId id() { return id; }
    public String nameStatus() { return nameStatus; }
    public LocalDateTime createdAt() { return createdAt; }
    public LocalDateTime updatedAt() { return updatedAt; }
}
