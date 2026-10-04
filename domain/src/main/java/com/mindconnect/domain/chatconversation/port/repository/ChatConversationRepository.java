package com.mindconnect.domain.chatconversation.port.repository;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.chatconversation.model.aggregate.ChatConversation;
import com.mindconnect.domain.chatconversation.model.valueobject.ChatConversationId;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y buscar ChatConversation.
 * Lo implementa la infraestructura.
 */
public interface ChatConversationRepository {

    ChatConversation save(ChatConversation aggregate);

    Optional<ChatConversation> findById(ChatConversationId id);

    List<ChatConversation> findAll();

    void delete(ChatConversation aggregate);
}
