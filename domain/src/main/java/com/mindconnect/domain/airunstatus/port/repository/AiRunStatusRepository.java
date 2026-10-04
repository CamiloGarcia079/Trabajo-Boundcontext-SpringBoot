package com.mindconnect.domain.airunstatus.port.repository;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.airunstatus.model.aggregate.AiRunStatus;
import com.mindconnect.domain.airunstatus.model.valueobject.AiRunStatusId;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y buscar AiRunStatus.
 * Lo implementa la infraestructura.
 */
public interface AiRunStatusRepository {

    AiRunStatus save(AiRunStatus aggregate);

    Optional<AiRunStatus> findById(AiRunStatusId id);

    List<AiRunStatus> findAll();

    void delete(AiRunStatus aggregate);
}
