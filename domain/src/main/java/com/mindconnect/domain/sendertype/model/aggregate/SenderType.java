package com.mindconnect.domain.sendertype.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.mindconnect.domain.common.model.AggregateRoot;
import com.mindconnect.domain.common.validation.DomainValidations;
import com.mindconnect.domain.sendertype.event.SenderTypeRegisteredEvent;
import com.mindconnect.domain.sendertype.event.SenderTypeUpdatedEvent;
import com.mindconnect.domain.sendertype.model.valueobject.SenderTypeId;

/**
 * Agregado raíz del contexto sendertype: representa la tabla sender_types.
 *
 * <p>El id y las fechas los pone el propio agregado. No usa Spring ni JPA.</p>
 */
public class SenderType extends AggregateRoot {

    private final SenderTypeId id;
    private String nameType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private SenderType(
            SenderTypeId id,
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
    public static SenderType register(
            String nameType) {
        SenderTypeId id = SenderTypeId.generate();
        LocalDateTime now = LocalDateTime.now();
        SenderType aggregate = new SenderType(id, nameType, now, now);
        aggregate.recordEvent(new SenderTypeRegisteredEvent(id, now));
        return aggregate;
    }

    /** Reconstruye un registro que ya existía (viene de la base de datos). No genera eventos. */
    public static SenderType restore(
            SenderTypeId id,
            String nameType,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new SenderType(id, nameType, createdAt, updatedAt);
    }

    /** Cambia los datos del registro, actualiza la fecha de modificación y deja el evento. */
    public void update(
            String nameType) {
        DomainValidations.required(nameType, "nameType");
        this.nameType = nameType;
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new SenderTypeUpdatedEvent(this.id, now));
    }

    public SenderTypeId id() { return id; }
    public String nameType() { return nameType; }
    public LocalDateTime createdAt() { return createdAt; }
    public LocalDateTime updatedAt() { return updatedAt; }
}
