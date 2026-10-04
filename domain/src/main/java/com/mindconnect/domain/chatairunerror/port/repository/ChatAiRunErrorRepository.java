package com.mindconnect.domain.chatairunerror.port.repository;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.chatairunerror.model.aggregate.ChatAiRunError;
import com.mindconnect.domain.chatairunerror.model.valueobject.ChatAiRunErrorId;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y buscar ChatAiRunError.
 * Lo implementa la infraestructura.
 */
public interface ChatAiRunErrorRepository {

    ChatAiRunError save(ChatAiRunError aggregate);

    Optional<ChatAiRunError> findById(ChatAiRunErrorId id);

    List<ChatAiRunError> findAll();

    void delete(ChatAiRunError aggregate);
}
