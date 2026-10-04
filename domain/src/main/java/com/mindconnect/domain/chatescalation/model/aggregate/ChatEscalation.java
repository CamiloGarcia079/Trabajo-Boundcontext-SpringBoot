package com.mindconnect.domain.chatescalation.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import com.mindconnect.domain.common.model.AggregateRoot;
import com.mindconnect.domain.common.validation.DomainValidations;
import com.mindconnect.domain.chatescalation.event.ChatEscalationRegisteredEvent;
import com.mindconnect.domain.chatescalation.event.ChatEscalationUpdatedEvent;
import com.mindconnect.domain.chatescalation.model.valueobject.ChatEscalationId;

/**
 * Agregado raíz del contexto chatescalation: representa la tabla chat_escalations.
 *
 * <p>Relaciones (se guardan solo por id, no como objetos):</p>
 * <ul>
 *   <li>conversationId -> ChatConversation</li>
 *   <li>statusId -> EscalationStatus</li>
 * </ul>
 *
 * <p>El id y las fechas los pone el propio agregado. No usa Spring ni JPA.</p>
 */
public class ChatEscalation extends AggregateRoot {

    private final ChatEscalationId id;
    private UUID conversationId;
    private UUID statusId;
    private boolean fromAi;
    private String reason;
    private LocalDateTime createdAt;

    private ChatEscalation(
            ChatEscalationId id,
            UUID conversationId,
            UUID statusId,
            boolean fromAi,
            String reason,
            LocalDateTime createdAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        DomainValidations.required(conversationId, "conversationId");
        DomainValidations.required(statusId, "statusId");
        DomainValidations.required(reason, "reason");
        DomainValidations.required(createdAt, "createdAt");
        this.conversationId = conversationId;
        this.statusId = statusId;
        this.fromAi = fromAi;
        this.reason = reason;
        this.createdAt = createdAt;
    }

    /** Crea un registro nuevo: genera el id y las fechas, y deja el evento de registro. */
    public static ChatEscalation register(
            UUID conversationId,
            UUID statusId,
            boolean fromAi,
            String reason) {
        ChatEscalationId id = ChatEscalationId.generate();
        LocalDateTime now = LocalDateTime.now();
        ChatEscalation aggregate = new ChatEscalation(id, conversationId, statusId, fromAi, reason, now);
        aggregate.recordEvent(new ChatEscalationRegisteredEvent(id, now));
        return aggregate;
    }

    /** Reconstruye un registro que ya existía (viene de la base de datos). No genera eventos. */
    public static ChatEscalation restore(
            ChatEscalationId id,
            UUID conversationId,
            UUID statusId,
            boolean fromAi,
            String reason,
            LocalDateTime createdAt) {
        return new ChatEscalation(id, conversationId, statusId, fromAi, reason, createdAt);
    }

    /** Cambia los datos del registro, actualiza la fecha de modificación y deja el evento. */
    public void update(
            UUID conversationId,
            UUID statusId,
            boolean fromAi,
            String reason) {
        DomainValidations.required(conversationId, "conversationId");
        DomainValidations.required(statusId, "statusId");
        DomainValidations.required(reason, "reason");
        this.conversationId = conversationId;
        this.statusId = statusId;
        this.fromAi = fromAi;
        this.reason = reason;
        LocalDateTime now = LocalDateTime.now();
        recordEvent(new ChatEscalationUpdatedEvent(this.id, now));
    }

    public ChatEscalationId id() { return id; }
    public UUID conversationId() { return conversationId; }
    public UUID statusId() { return statusId; }
    public boolean fromAi() { return fromAi; }
    public String reason() { return reason; }
    public LocalDateTime createdAt() { return createdAt; }
}
