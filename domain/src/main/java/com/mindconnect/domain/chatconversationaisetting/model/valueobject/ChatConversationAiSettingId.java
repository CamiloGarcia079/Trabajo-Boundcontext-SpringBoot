package com.mindconnect.domain.chatconversationaisetting.model.valueobject;

import java.util.Objects;
import java.util.UUID;

/**
 * Identificador de ChatConversationAiSetting. Envuelve el UUID para que no se confunda con el id de otro agregado.
 */
public record ChatConversationAiSettingId(UUID value) {

    public ChatConversationAiSettingId {
        Objects.requireNonNull(value, "id must not be null");
    }

    public static ChatConversationAiSettingId generate() {
        return new ChatConversationAiSettingId(UUID.randomUUID());
    }
}
