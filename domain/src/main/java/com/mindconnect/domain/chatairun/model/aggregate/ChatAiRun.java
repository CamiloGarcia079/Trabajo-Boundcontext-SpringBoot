package com.mindconnect.domain.chatairun.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import com.mindconnect.domain.common.model.AggregateRoot;
import com.mindconnect.domain.common.validation.DomainValidations;
import com.mindconnect.domain.chatairun.event.ChatAiRunRegisteredEvent;
import com.mindconnect.domain.chatairun.event.ChatAiRunUpdatedEvent;
import com.mindconnect.domain.chatairun.model.valueobject.ChatAiRunId;

/**
 * Agregado raíz del contexto chatairun: representa la tabla chat_ai_runs.
 *
 * <p>Relaciones (se guardan solo por id, no como objetos):</p>
 * <ul>
 *   <li>conversationId -> ChatConversation</li>
 *   <li>messageId -> ChatMessage</li>
 *   <li>modelId -> AiModel</li>
 *   <li>aiRunStatusId -> AiRunStatus</li>
 * </ul>
 *
 * <p>El id y las fechas los pone el propio agregado. No usa Spring ni JPA.</p>
 */
public class ChatAiRun extends AggregateRoot {

    private final ChatAiRunId id;
    private UUID conversationId;
    private UUID messageId;
    private UUID modelId;
    private UUID aiRunStatusId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private ChatAiRun(
            ChatAiRunId id,
            UUID conversationId,
            UUID messageId,
            UUID modelId,
            UUID aiRunStatusId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        DomainValidations.required(conversationId, "conversationId");
        DomainValidations.required(messageId, "messageId");
        DomainValidations.required(modelId, "modelId");
        DomainValidations.required(aiRunStatusId, "aiRunStatusId");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(updatedAt, "updatedAt");
        this.conversationId = conversationId;
        this.messageId = messageId;
        this.modelId = modelId;
        this.aiRunStatusId = aiRunStatusId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /** Crea un registro nuevo: genera el id y las fechas, y deja el evento de registro. */
    public static ChatAiRun register(
            UUID conversationId,
            UUID messageId,
            UUID modelId,
            UUID aiRunStatusId) {
        ChatAiRunId id = ChatAiRunId.generate();
        LocalDateTime now = LocalDateTime.now();
        ChatAiRun aggregate = new ChatAiRun(id, conversationId, messageId, modelId, aiRunStatusId, now, now);
        aggregate.recordEvent(new ChatAiRunRegisteredEvent(id, now));
        return aggregate;
    }

    /** Reconstruye un registro que ya existía (viene de la base de datos). No genera eventos. */
    public static ChatAiRun restore(
            ChatAiRunId id,
            UUID conversationId,
            UUID messageId,
            UUID modelId,
            UUID aiRunStatusId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new ChatAiRun(id, conversationId, messageId, modelId, aiRunStatusId, createdAt, updatedAt);
    }

    /** Cambia los datos del registro, actualiza la fecha de modificación y deja el evento. */
    public void update(
            UUID conversationId,
            UUID messageId,
            UUID modelId,
            UUID aiRunStatusId) {
        DomainValidations.required(conversationId, "conversationId");
        DomainValidations.required(messageId, "messageId");
        DomainValidations.required(modelId, "modelId");
        DomainValidations.required(aiRunStatusId, "aiRunStatusId");
        this.conversationId = conversationId;
        this.messageId = messageId;
        this.modelId = modelId;
        this.aiRunStatusId = aiRunStatusId;
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new ChatAiRunUpdatedEvent(this.id, now));
    }

    public ChatAiRunId id() { return id; }
    public UUID conversationId() { return conversationId; }
    public UUID messageId() { return messageId; }
    public UUID modelId() { return modelId; }
    public UUID aiRunStatusId() { return aiRunStatusId; }
    public LocalDateTime createdAt() { return createdAt; }
    public LocalDateTime updatedAt() { return updatedAt; }
}
