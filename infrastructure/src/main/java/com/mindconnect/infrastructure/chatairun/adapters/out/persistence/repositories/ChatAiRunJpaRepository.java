package com.mindconnect.infrastructure.chatairun.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindconnect.infrastructure.chatairun.adapters.out.persistence.entity.ChatAiRunJpaEntity;

/**
 * Repositorio de Spring Data para chat_ai_runs. Spring genera la implementación.
 */
public interface ChatAiRunJpaRepository extends JpaRepository<ChatAiRunJpaEntity, UUID> {
}
