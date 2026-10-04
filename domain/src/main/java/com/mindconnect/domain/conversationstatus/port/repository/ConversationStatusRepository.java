package com.mindconnect.domain.conversationstatus.port.repository;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.conversationstatus.model.aggregate.ConversationStatus;
import com.mindconnect.domain.conversationstatus.model.valueobject.ConversationStatusId;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y buscar ConversationStatus.
 * Lo implementa la infraestructura.
 */
public interface ConversationStatusRepository {

    ConversationStatus save(ConversationStatus aggregate);

    Optional<ConversationStatus> findById(ConversationStatusId id);

    List<ConversationStatus> findAll();

    void delete(ConversationStatus aggregate);
}
