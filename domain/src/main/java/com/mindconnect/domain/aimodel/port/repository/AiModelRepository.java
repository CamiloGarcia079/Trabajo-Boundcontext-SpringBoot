package com.mindconnect.domain.aimodel.port.repository;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.aimodel.model.aggregate.AiModel;
import com.mindconnect.domain.aimodel.model.valueobject.AiModelId;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y buscar AiModel.
 * Lo implementa la infraestructura.
 */
public interface AiModelRepository {

    AiModel save(AiModel aggregate);

    Optional<AiModel> findById(AiModelId id);

    List<AiModel> findAll();

    void delete(AiModel aggregate);
}
