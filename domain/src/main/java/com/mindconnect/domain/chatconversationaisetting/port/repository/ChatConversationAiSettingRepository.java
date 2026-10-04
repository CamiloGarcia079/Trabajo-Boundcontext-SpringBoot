package com.mindconnect.domain.chatconversationaisetting.port.repository;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.chatconversationaisetting.model.aggregate.ChatConversationAiSetting;
import com.mindconnect.domain.chatconversationaisetting.model.valueobject.ChatConversationAiSettingId;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y buscar ChatConversationAiSetting.
 * Lo implementa la infraestructura.
 */
public interface ChatConversationAiSettingRepository {

    ChatConversationAiSetting save(ChatConversationAiSetting aggregate);

    Optional<ChatConversationAiSetting> findById(ChatConversationAiSettingId id);

    List<ChatConversationAiSetting> findAll();

    void delete(ChatConversationAiSetting aggregate);
}
