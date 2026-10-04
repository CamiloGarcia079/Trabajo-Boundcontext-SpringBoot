package com.mindconnect.infrastructure.chatparticipant.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindconnect.infrastructure.chatparticipant.adapters.out.persistence.entity.ChatParticipantJpaEntity;

/**
 * Repositorio de Spring Data para chat_participants. Spring genera la implementación.
 */
public interface ChatParticipantJpaRepository extends JpaRepository<ChatParticipantJpaEntity, UUID> {
}
