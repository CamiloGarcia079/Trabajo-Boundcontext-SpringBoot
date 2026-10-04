package com.mindconnect.domain.chatparticipant.port.repository;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.chatparticipant.model.aggregate.ChatParticipant;
import com.mindconnect.domain.chatparticipant.model.valueobject.ChatParticipantId;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y buscar ChatParticipant.
 * Lo implementa la infraestructura.
 */
public interface ChatParticipantRepository {

    ChatParticipant save(ChatParticipant aggregate);

    Optional<ChatParticipant> findById(ChatParticipantId id);

    List<ChatParticipant> findAll();

    void delete(ChatParticipant aggregate);
}
