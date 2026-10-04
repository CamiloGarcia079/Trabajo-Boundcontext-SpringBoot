package com.mindconnect.domain.chatairun.port.repository;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.chatairun.model.aggregate.ChatAiRun;
import com.mindconnect.domain.chatairun.model.valueobject.ChatAiRunId;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y buscar ChatAiRun.
 * Lo implementa la infraestructura.
 */
public interface ChatAiRunRepository {

    ChatAiRun save(ChatAiRun aggregate);

    Optional<ChatAiRun> findById(ChatAiRunId id);

    List<ChatAiRun> findAll();

    void delete(ChatAiRun aggregate);
}
