package com.mindconnect.domain.chatmessage.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import com.mindconnect.domain.common.model.AggregateRoot;
import com.mindconnect.domain.common.validation.DomainValidations;
import com.mindconnect.domain.chatmessage.event.ChatMessageRegisteredEvent;
import com.mindconnect.domain.chatmessage.event.ChatMessageUpdatedEvent;
import com.mindconnect.domain.chatmessage.model.valueobject.ChatMessageId;

/**
 * Agregado raíz del contexto chatmessage: representa la tabla chat_messages.
 *
 * <p>Relaciones (se guardan solo por id, no como objetos):</p>
 * <ul>
 *   <li>conversationId -> ChatConversation</li>
 *   <li>messageTypeId -> MessageType</li>
 *   <li>participantId -> ChatParticipant</li>
 * </ul>
 *
 * <p>El id y las fechas los pone el propio agregado. No usa Spring ni JPA.</p>
 */
public class ChatMessage extends AggregateRoot {

    private final ChatMessageId id;
    private UUID conversationId;
    private UUID messageTypeId;
    private UUID participantId;
    private String content;
    private String metadata;
    private LocalDateTime createdAt;

    private ChatMessage(
            ChatMessageId id,
            UUID conversationId,
            UUID messageTypeId,
            UUID participantId,
            String content,
            String metadata,
            LocalDateTime createdAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        DomainValidations.required(conversationId, "conversationId");
        DomainValidations.required(messageTypeId, "messageTypeId");
        DomainValidations.required(participantId, "participantId");
        DomainValidations.required(content, "content");
        DomainValidations.required(metadata, "metadata");
        DomainValidations.required(createdAt, "createdAt");
        this.conversationId = conversationId;
        this.messageTypeId = messageTypeId;
        this.participantId = participantId;
        this.content = content;
        this.metadata = metadata;
        this.createdAt = createdAt;
    }

    /** Crea un registro nuevo: genera el id y las fechas, y deja el evento de registro. */
    public static ChatMessage register(
            UUID conversationId,
            UUID messageTypeId,
            UUID participantId,
            String content,
            String metadata) {
        ChatMessageId id = ChatMessageId.generate();
        LocalDateTime now = LocalDateTime.now();
        ChatMessage aggregate = new ChatMessage(id, conversationId, messageTypeId, participantId, content, metadata, now);
        aggregate.recordEvent(new ChatMessageRegisteredEvent(id, now));
        return aggregate;
    }

    /** Reconstruye un registro que ya existía (viene de la base de datos). No genera eventos. */
    public static ChatMessage restore(
            ChatMessageId id,
            UUID conversationId,
            UUID messageTypeId,
            UUID participantId,
            String content,
            String metadata,
            LocalDateTime createdAt) {
        return new ChatMessage(id, conversationId, messageTypeId, participantId, content, metadata, createdAt);
    }

    /** Cambia los datos del registro, actualiza la fecha de modificación y deja el evento. */
    public void update(
            UUID conversationId,
            UUID messageTypeId,
            UUID participantId,
            String content,
            String metadata) {
        DomainValidations.required(conversationId, "conversationId");
        DomainValidations.required(messageTypeId, "messageTypeId");
        DomainValidations.required(participantId, "participantId");
        DomainValidations.required(content, "content");
        DomainValidations.required(metadata, "metadata");
        this.conversationId = conversationId;
        this.messageTypeId = messageTypeId;
        this.participantId = participantId;
        this.content = content;
        this.metadata = metadata;
        LocalDateTime now = LocalDateTime.now();
        recordEvent(new ChatMessageUpdatedEvent(this.id, now));
    }

    public ChatMessageId id() { return id; }
    public UUID conversationId() { return conversationId; }
    public UUID messageTypeId() { return messageTypeId; }
    public UUID participantId() { return participantId; }
    public String content() { return content; }
    public String metadata() { return metadata; }
    public LocalDateTime createdAt() { return createdAt; }
}
