package com.mindconnect.infrastructure.chatescalationassignment.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindconnect.infrastructure.chatescalationassignment.adapters.out.persistence.entity.ChatEscalationAssignmentJpaEntity;

/**
 * Repositorio de Spring Data para chat_escalation_assignments. Spring genera la implementación.
 */
public interface ChatEscalationAssignmentJpaRepository extends JpaRepository<ChatEscalationAssignmentJpaEntity, UUID> {
}
