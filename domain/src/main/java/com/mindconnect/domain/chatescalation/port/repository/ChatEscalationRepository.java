package com.mindconnect.domain.chatescalation.port.repository;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.chatescalation.model.aggregate.ChatEscalation;
import com.mindconnect.domain.chatescalation.model.valueobject.ChatEscalationId;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y buscar ChatEscalation.
 * Lo implementa la infraestructura.
 */
public interface ChatEscalationRepository {

    ChatEscalation save(ChatEscalation aggregate);

    Optional<ChatEscalation> findById(ChatEscalationId id);

    List<ChatEscalation> findAll();

    void delete(ChatEscalation aggregate);
}
