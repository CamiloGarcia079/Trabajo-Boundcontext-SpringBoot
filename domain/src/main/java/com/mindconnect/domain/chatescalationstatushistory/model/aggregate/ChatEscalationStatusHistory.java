package com.mindconnect.domain.chatescalationstatushistory.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import com.mindconnect.domain.common.model.AggregateRoot;
import com.mindconnect.domain.common.validation.DomainValidations;
import com.mindconnect.domain.chatescalationstatushistory.event.ChatEscalationStatusHistoryRegisteredEvent;
import com.mindconnect.domain.chatescalationstatushistory.event.ChatEscalationStatusHistoryUpdatedEvent;
import com.mindconnect.domain.chatescalationstatushistory.model.valueobject.ChatEscalationStatusHistoryId;

/**
 * Agregado raíz del contexto chatescalationstatushistory: representa la tabla chat_escalation_status_history.
 *
 * <p>Relaciones (se guardan solo por id, no como objetos):</p>
 * <ul>
 *   <li>escalationId -> ChatEscalation</li>
 *   <li>escalationStatusId -> EscalationStatus</li>
 * </ul>
 *
 * <p>El id y las fechas los pone el propio agregado. No usa Spring ni JPA.</p>
 */
public class ChatEscalationStatusHistory extends AggregateRoot {

    private final ChatEscalationStatusHistoryId id;
    private UUID escalationId;
    private UUID escalationStatusId;
    private LocalDateTime createdAt;
    private LocalDateTime changedAt;

    private ChatEscalationStatusHistory(
            ChatEscalationStatusHistoryId id,
            UUID escalationId,
            UUID escalationStatusId,
            LocalDateTime createdAt,
            LocalDateTime changedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        DomainValidations.required(escalationId, "escalationId");
        DomainValidations.required(escalationStatusId, "escalationStatusId");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(changedAt, "changedAt");
        this.escalationId = escalationId;
        this.escalationStatusId = escalationStatusId;
        this.createdAt = createdAt;
        this.changedAt = changedAt;
    }

    /** Crea un registro nuevo: genera el id y las fechas, y deja el evento de registro. */
    public static ChatEscalationStatusHistory register(
            UUID escalationId,
            UUID escalationStatusId,
            LocalDateTime changedAt) {
        ChatEscalationStatusHistoryId id = ChatEscalationStatusHistoryId.generate();
        LocalDateTime now = LocalDateTime.now();
        ChatEscalationStatusHistory aggregate = new ChatEscalationStatusHistory(id, escalationId, escalationStatusId, now, changedAt);
        aggregate.recordEvent(new ChatEscalationStatusHistoryRegisteredEvent(id, now));
        return aggregate;
    }

    /** Reconstruye un registro que ya existía (viene de la base de datos). No genera eventos. */
    public static ChatEscalationStatusHistory restore(
            ChatEscalationStatusHistoryId id,
            UUID escalationId,
            UUID escalationStatusId,
            LocalDateTime createdAt,
            LocalDateTime changedAt) {
        return new ChatEscalationStatusHistory(id, escalationId, escalationStatusId, createdAt, changedAt);
    }

    /** Cambia los datos del registro, actualiza la fecha de modificación y deja el evento. */
    public void update(
            UUID escalationId,
            UUID escalationStatusId,
            LocalDateTime changedAt) {
        DomainValidations.required(escalationId, "escalationId");
        DomainValidations.required(escalationStatusId, "escalationStatusId");
        DomainValidations.required(changedAt, "changedAt");
        this.escalationId = escalationId;
        this.escalationStatusId = escalationStatusId;
        this.changedAt = changedAt;
        LocalDateTime now = LocalDateTime.now();
        recordEvent(new ChatEscalationStatusHistoryUpdatedEvent(this.id, now));
    }

    public ChatEscalationStatusHistoryId id() { return id; }
    public UUID escalationId() { return escalationId; }
    public UUID escalationStatusId() { return escalationStatusId; }
    public LocalDateTime createdAt() { return createdAt; }
    public LocalDateTime changedAt() { return changedAt; }
}
