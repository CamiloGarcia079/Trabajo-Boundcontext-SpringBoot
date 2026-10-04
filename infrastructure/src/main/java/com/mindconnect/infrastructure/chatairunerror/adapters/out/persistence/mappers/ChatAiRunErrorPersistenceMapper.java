package com.mindconnect.infrastructure.chatairunerror.adapters.out.persistence.mappers;

import com.mindconnect.domain.chatairunerror.model.aggregate.ChatAiRunError;
import com.mindconnect.domain.chatairunerror.model.valueobject.ChatAiRunErrorId;
import com.mindconnect.infrastructure.chatairunerror.adapters.out.persistence.entity.ChatAiRunErrorJpaEntity;

/**
 * Convierte entre el agregado de dominio y la entidad JPA de chat_ai_run_errors.
 */
public class ChatAiRunErrorPersistenceMapper {

    public ChatAiRunErrorJpaEntity toJpa(ChatAiRunError domain) {
        if (domain == null) {
            return null;
        }

        ChatAiRunErrorJpaEntity jpa = new ChatAiRunErrorJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setAiRunId(domain.aiRunId());
        jpa.setErrorMessage(domain.errorMessage());
        jpa.setErrorCode(domain.errorCode());
        jpa.setProviderErrorId(domain.providerErrorId());
        jpa.setCreatedAt(domain.createdAt());

        return jpa;
    }

    public ChatAiRunError toDomain(ChatAiRunErrorJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }

        return ChatAiRunError.restore(
                new ChatAiRunErrorId(jpa.getId()),
                jpa.getAiRunId(), jpa.getErrorMessage(), jpa.getErrorCode(), jpa.getProviderErrorId(), jpa.getCreatedAt());
    }
}
