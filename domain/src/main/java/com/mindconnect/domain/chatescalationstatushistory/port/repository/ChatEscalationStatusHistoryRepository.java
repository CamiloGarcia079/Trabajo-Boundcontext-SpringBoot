package com.mindconnect.domain.chatescalationstatushistory.port.repository;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.chatescalationstatushistory.model.aggregate.ChatEscalationStatusHistory;
import com.mindconnect.domain.chatescalationstatushistory.model.valueobject.ChatEscalationStatusHistoryId;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y buscar ChatEscalationStatusHistory.
 * Lo implementa la infraestructura.
 */
public interface ChatEscalationStatusHistoryRepository {

    ChatEscalationStatusHistory save(ChatEscalationStatusHistory aggregate);

    Optional<ChatEscalationStatusHistory> findById(ChatEscalationStatusHistoryId id);

    List<ChatEscalationStatusHistory> findAll();

    void delete(ChatEscalationStatusHistory aggregate);
}
