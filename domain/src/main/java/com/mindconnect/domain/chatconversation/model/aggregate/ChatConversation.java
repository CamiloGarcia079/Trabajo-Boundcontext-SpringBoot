package com.mindconnect.domain.chatconversation.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import com.mindconnect.domain.common.model.AggregateRoot;
import com.mindconnect.domain.common.validation.DomainValidations;
import com.mindconnect.domain.chatconversation.event.ChatConversationRegisteredEvent;
import com.mindconnect.domain.chatconversation.event.ChatConversationUpdatedEvent;
import com.mindconnect.domain.chatconversation.model.valueobject.ChatConversationId;

/**
 * Agregado raíz del contexto chatconversation: representa la tabla chat_conversations.
 *
 * <p>Relaciones (se guardan solo por id, no como objetos):</p>
 * <ul>
 *   <li>conversationStatusId -> ConversationStatus</li>
 *   <li>priorityId -> Priority</li>
 * </ul>
 *
 * <p>El id y las fechas los pone el propio agregado. No usa Spring ni JPA.</p>
 */
public class ChatConversation extends AggregateRoot {

    private final ChatConversationId id;
    private UUID conversationStatusId;
    private UUID priorityId;
    private LocalDateTime lastMessageAt;
    private Boolean closed;
    private LocalDateTime closedAt;
    private UUID closedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private ChatConversation(
            ChatConversationId id,
            UUID conversationStatusId,
            UUID priorityId,
            LocalDateTime lastMessageAt,
            Boolean closed,
            LocalDateTime closedAt,
            UUID closedBy,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        DomainValidations.required(conversationStatusId, "conversationStatusId");
        DomainValidations.required(priorityId, "priorityId");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(updatedAt, "updatedAt");
        this.conversationStatusId = conversationStatusId;
        this.priorityId = priorityId;
        this.lastMessageAt = lastMessageAt;
        this.closed = closed;
        this.closedAt = closedAt;
        this.closedBy = closedBy;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /** Crea un registro nuevo: genera el id y las fechas, y deja el evento de registro. */
    public static ChatConversation register(
            UUID conversationStatusId,
            UUID priorityId,
            LocalDateTime lastMessageAt,
            Boolean closed,
            LocalDateTime closedAt,
            UUID closedBy) {
        ChatConversationId id = ChatConversationId.generate();
        LocalDateTime now = LocalDateTime.now();
        ChatConversation aggregate = new ChatConversation(id, conversationStatusId, priorityId, lastMessageAt, closed, closedAt, closedBy, now, now);
        aggregate.recordEvent(new ChatConversationRegisteredEvent(id, now));
        return aggregate;
    }

    /** Reconstruye un registro que ya existía (viene de la base de datos). No genera eventos. */
    public static ChatConversation restore(
            ChatConversationId id,
            UUID conversationStatusId,
            UUID priorityId,
            LocalDateTime lastMessageAt,
            Boolean closed,
            LocalDateTime closedAt,
            UUID closedBy,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new ChatConversation(id, conversationStatusId, priorityId, lastMessageAt, closed, closedAt, closedBy, createdAt, updatedAt);
    }

    /** Cambia los datos del registro, actualiza la fecha de modificación y deja el evento. */
    public void update(
            UUID conversationStatusId,
            UUID priorityId,
            LocalDateTime lastMessageAt,
            Boolean closed,
            LocalDateTime closedAt,
            UUID closedBy) {
        DomainValidations.required(conversationStatusId, "conversationStatusId");
        DomainValidations.required(priorityId, "priorityId");
        this.conversationStatusId = conversationStatusId;
        this.priorityId = priorityId;
        this.lastMessageAt = lastMessageAt;
        this.closed = closed;
        this.closedAt = closedAt;
        this.closedBy = closedBy;
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new ChatConversationUpdatedEvent(this.id, now));
    }

    public ChatConversationId id() { return id; }
    public UUID conversationStatusId() { return conversationStatusId; }
    public UUID priorityId() { return priorityId; }
    public LocalDateTime lastMessageAt() { return lastMessageAt; }
    public Boolean closed() { return closed; }
    public LocalDateTime closedAt() { return closedAt; }
    public UUID closedBy() { return closedBy; }
    public LocalDateTime createdAt() { return createdAt; }
    public LocalDateTime updatedAt() { return updatedAt; }
}
