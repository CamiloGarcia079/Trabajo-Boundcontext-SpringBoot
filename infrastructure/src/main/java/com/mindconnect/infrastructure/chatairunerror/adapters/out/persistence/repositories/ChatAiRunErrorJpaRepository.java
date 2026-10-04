package com.mindconnect.infrastructure.chatairunerror.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindconnect.infrastructure.chatairunerror.adapters.out.persistence.entity.ChatAiRunErrorJpaEntity;

/**
 * Repositorio de Spring Data para chat_ai_run_errors. Spring genera la implementación.
 */
public interface ChatAiRunErrorJpaRepository extends JpaRepository<ChatAiRunErrorJpaEntity, UUID> {
}
