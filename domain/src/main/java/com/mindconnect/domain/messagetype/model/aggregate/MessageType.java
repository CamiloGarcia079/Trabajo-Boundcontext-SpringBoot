package com.mindconnect.domain.messagetype.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.mindconnect.domain.common.model.AggregateRoot;
import com.mindconnect.domain.common.validation.DomainValidations;
import com.mindconnect.domain.messagetype.event.MessageTypeRegisteredEvent;
import com.mindconnect.domain.messagetype.event.MessageTypeUpdatedEvent;
import com.mindconnect.domain.messagetype.model.valueobject.MessageTypeId;

/**
 * Agregado raíz del contexto messagetype: representa la tabla message_types.
 *
 * <p>El id y las fechas los pone el propio agregado. No usa Spring ni JPA.</p>
 */
public class MessageType extends AggregateRoot {

    private final MessageTypeId id;
    private String nameType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private MessageType(
            MessageTypeId id,
            String nameType,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        DomainValidations.required(nameType, "nameType");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(updatedAt, "updatedAt");
        this.nameType = nameType;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /** Crea un registro nuevo: genera el id y las fechas, y deja el evento de registro. */
    public static MessageType register(
            String nameType) {
        MessageTypeId id = MessageTypeId.generate();
        LocalDateTime now = LocalDateTime.now();
        MessageType aggregate = new MessageType(id, nameType, now, now);
        aggregate.recordEvent(new MessageTypeRegisteredEvent(id, now));
        return aggregate;
    }

    /** Reconstruye un registro que ya existía (viene de la base de datos). No genera eventos. */
    public static MessageType restore(
            MessageTypeId id,
            String nameType,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new MessageType(id, nameType, createdAt, updatedAt);
    }

    /** Cambia los datos del registro, actualiza la fecha de modificación y deja el evento. */
    public void update(
            String nameType) {
        DomainValidations.required(nameType, "nameType");
        this.nameType = nameType;
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new MessageTypeUpdatedEvent(this.id, now));
    }

    public MessageTypeId id() { return id; }
    public String nameType() { return nameType; }
    public LocalDateTime createdAt() { return createdAt; }
    public LocalDateTime updatedAt() { return updatedAt; }
}
