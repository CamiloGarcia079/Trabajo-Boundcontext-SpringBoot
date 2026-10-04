package com.mindconnect.domain.chatmessage.port.repository;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.chatmessage.model.aggregate.ChatMessage;
import com.mindconnect.domain.chatmessage.model.valueobject.ChatMessageId;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y buscar ChatMessage.
 * Lo implementa la infraestructura.
 */
public interface ChatMessageRepository {

    ChatMessage save(ChatMessage aggregate);

    Optional<ChatMessage> findById(ChatMessageId id);

    List<ChatMessage> findAll();

    void delete(ChatMessage aggregate);
}
