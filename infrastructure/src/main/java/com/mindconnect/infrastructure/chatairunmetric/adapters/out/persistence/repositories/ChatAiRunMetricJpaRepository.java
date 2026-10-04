package com.mindconnect.infrastructure.chatairunmetric.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindconnect.infrastructure.chatairunmetric.adapters.out.persistence.entity.ChatAiRunMetricJpaEntity;

/**
 * Repositorio de Spring Data para chat_ai_run_metrics. Spring genera la implementación.
 */
public interface ChatAiRunMetricJpaRepository extends JpaRepository<ChatAiRunMetricJpaEntity, UUID> {
}
