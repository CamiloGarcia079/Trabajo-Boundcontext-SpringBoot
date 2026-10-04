package com.mindconnect.domain.chatconversationaisetting.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import com.mindconnect.domain.common.model.AggregateRoot;
import com.mindconnect.domain.common.validation.DomainValidations;
import com.mindconnect.domain.chatconversationaisetting.event.ChatConversationAiSettingRegisteredEvent;
import com.mindconnect.domain.chatconversationaisetting.event.ChatConversationAiSettingUpdatedEvent;
import com.mindconnect.domain.chatconversationaisetting.model.valueobject.ChatConversationAiSettingId;

/**
 * Agregado raíz del contexto chatconversationaisetting: representa la tabla chat_conversation_ai_settings.
 *
 * <p>Relaciones (se guardan solo por id, no como objetos):</p>
 * <ul>
 *   <li>conversationId -> ChatConversation</li>
 *   <li>defaultModelId -> AiModel</li>
 * </ul>
 *
 * <p>El id y las fechas los pone el propio agregado. No usa Spring ni JPA.</p>
 */
public class ChatConversationAiSetting extends AggregateRoot {

    private final ChatConversationAiSettingId id;
    private UUID conversationId;
    private boolean aiEnabled;
    private UUID defaultModelId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private ChatConversationAiSetting(
            ChatConversationAiSettingId id,
            UUID conversationId,
            boolean aiEnabled,
            UUID defaultModelId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        DomainValidations.required(conversationId, "conversationId");
        DomainValidations.required(defaultModelId, "defaultModelId");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(updatedAt, "updatedAt");
        this.conversationId = conversationId;
        this.aiEnabled = aiEnabled;
        this.defaultModelId = defaultModelId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /** Crea un registro nuevo: genera el id y las fechas, y deja el evento de registro. */
    public static ChatConversationAiSetting register(
            UUID conversationId,
            boolean aiEnabled,
            UUID defaultModelId) {
        ChatConversationAiSettingId id = ChatConversationAiSettingId.generate();
        LocalDateTime now = LocalDateTime.now();
        ChatConversationAiSetting aggregate = new ChatConversationAiSetting(id, conversationId, aiEnabled, defaultModelId, now, now);
        aggregate.recordEvent(new ChatConversationAiSettingRegisteredEvent(id, now));
        return aggregate;
    }

    /** Reconstruye un registro que ya existía (viene de la base de datos). No genera eventos. */
    public static ChatConversationAiSetting restore(
            ChatConversationAiSettingId id,
            UUID conversationId,
            boolean aiEnabled,
            UUID defaultModelId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new ChatConversationAiSetting(id, conversationId, aiEnabled, defaultModelId, createdAt, updatedAt);
    }

    /** Cambia los datos del registro, actualiza la fecha de modificación y deja el evento. */
    public void update(
            UUID conversationId,
            boolean aiEnabled,
            UUID defaultModelId) {
        DomainValidations.required(conversationId, "conversationId");
        DomainValidations.required(defaultModelId, "defaultModelId");
        this.conversationId = conversationId;
        this.aiEnabled = aiEnabled;
        this.defaultModelId = defaultModelId;
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new ChatConversationAiSettingUpdatedEvent(this.id, now));
    }

    public ChatConversationAiSettingId id() { return id; }
    public UUID conversationId() { return conversationId; }
    public boolean aiEnabled() { return aiEnabled; }
    public UUID defaultModelId() { return defaultModelId; }
    public LocalDateTime createdAt() { return createdAt; }
    public LocalDateTime updatedAt() { return updatedAt; }
}
