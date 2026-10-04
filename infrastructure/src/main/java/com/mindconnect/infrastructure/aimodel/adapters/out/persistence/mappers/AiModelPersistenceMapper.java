package com.mindconnect.infrastructure.aimodel.adapters.out.persistence.mappers;

import com.mindconnect.domain.aimodel.model.aggregate.AiModel;
import com.mindconnect.domain.aimodel.model.valueobject.AiModelId;
import com.mindconnect.infrastructure.aimodel.adapters.out.persistence.entity.AiModelJpaEntity;

/**
 * Convierte entre el agregado de dominio y la entidad JPA de ai_models.
 */
public class AiModelPersistenceMapper {

    public AiModelJpaEntity toJpa(AiModel domain) {
        if (domain == null) {
            return null;
        }

        AiModelJpaEntity jpa = new AiModelJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setProviderModelId(domain.providerModelId());
        jpa.setNameModel(domain.nameModel());
        jpa.setModelKey(domain.modelKey());
        jpa.setInputTokenPrice(domain.inputTokenPrice());
        jpa.setOutputTokenPrice(domain.outputTokenPrice());
        jpa.setMaxTokens(domain.maxTokens());
        jpa.setContextWindow(domain.contextWindow());
        jpa.setIsActive(domain.isActive());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());

        return jpa;
    }

    public AiModel toDomain(AiModelJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }

        return AiModel.restore(
                new AiModelId(jpa.getId()),
                jpa.getProviderModelId(), jpa.getNameModel(), jpa.getModelKey(), jpa.getInputTokenPrice(), jpa.getOutputTokenPrice(), jpa.getMaxTokens(), jpa.getContextWindow(), jpa.getIsActive(), jpa.getCreatedAt(), jpa.getUpdatedAt());
    }
}
