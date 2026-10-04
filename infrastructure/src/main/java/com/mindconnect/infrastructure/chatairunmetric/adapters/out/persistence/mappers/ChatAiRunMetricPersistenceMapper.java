package com.mindconnect.infrastructure.chatairunmetric.adapters.out.persistence.mappers;

import com.mindconnect.domain.chatairunmetric.model.aggregate.ChatAiRunMetric;
import com.mindconnect.domain.chatairunmetric.model.valueobject.ChatAiRunMetricId;
import com.mindconnect.infrastructure.chatairunmetric.adapters.out.persistence.entity.ChatAiRunMetricJpaEntity;

/**
 * Convierte entre el agregado de dominio y la entidad JPA de chat_ai_run_metrics.
 */
public class ChatAiRunMetricPersistenceMapper {

    public ChatAiRunMetricJpaEntity toJpa(ChatAiRunMetric domain) {
        if (domain == null) {
            return null;
        }

        ChatAiRunMetricJpaEntity jpa = new ChatAiRunMetricJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setAiRunId(domain.aiRunId());
        jpa.setPromptTokens(domain.promptTokens());
        jpa.setCompletionTokens(domain.completionTokens());
        jpa.setTotalTokens(domain.totalTokens());
        jpa.setCost(domain.cost());
        jpa.setCreatedAt(domain.createdAt());

        return jpa;
    }

    public ChatAiRunMetric toDomain(ChatAiRunMetricJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }

        return ChatAiRunMetric.restore(
                new ChatAiRunMetricId(jpa.getId()),
                jpa.getAiRunId(), jpa.getPromptTokens(), jpa.getCompletionTokens(), jpa.getTotalTokens(), jpa.getCost(), jpa.getCreatedAt());
    }
}
