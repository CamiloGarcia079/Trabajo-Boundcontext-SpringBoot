package com.mindconnect.domain.conversationstatus.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.mindconnect.domain.common.model.AggregateRoot;
import com.mindconnect.domain.common.validation.DomainValidations;
import com.mindconnect.domain.conversationstatus.event.ConversationStatusRegisteredEvent;
import com.mindconnect.domain.conversationstatus.event.ConversationStatusUpdatedEvent;
import com.mindconnect.domain.conversationstatus.model.valueobject.ConversationStatusId;

/**
 * Agregado raíz del contexto conversationstatus: representa la tabla conversations_statuses.
 *
 * <p>El id y las fechas los pone el propio agregado. No usa Spring ni JPA.</p>
 */
public class ConversationStatus extends AggregateRoot {

    private final ConversationStatusId id;
    private String nameStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private ConversationStatus(
            ConversationStatusId id,
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
    public static ConversationStatus register(
            String nameStatus) {
        ConversationStatusId id = ConversationStatusId.generate();
        LocalDateTime now = LocalDateTime.now();
        ConversationStatus aggregate = new ConversationStatus(id, nameStatus, now, now);
        aggregate.recordEvent(new ConversationStatusRegisteredEvent(id, now));
        return aggregate;
    }

    /** Reconstruye un registro que ya existía (viene de la base de datos). No genera eventos. */
    public static ConversationStatus restore(
            ConversationStatusId id,
            String nameStatus,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new ConversationStatus(id, nameStatus, createdAt, updatedAt);
    }

    /** Cambia los datos del registro, actualiza la fecha de modificación y deja el evento. */
    public void update(
            String nameStatus) {
        DomainValidations.required(nameStatus, "nameStatus");
        this.nameStatus = nameStatus;
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new ConversationStatusUpdatedEvent(this.id, now));
    }

    public ConversationStatusId id() { return id; }
    public String nameStatus() { return nameStatus; }
    public LocalDateTime createdAt() { return createdAt; }
    public LocalDateTime updatedAt() { return updatedAt; }
}
