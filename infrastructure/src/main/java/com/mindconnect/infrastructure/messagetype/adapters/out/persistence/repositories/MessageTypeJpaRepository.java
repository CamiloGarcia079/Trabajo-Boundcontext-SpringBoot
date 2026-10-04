package com.mindconnect.infrastructure.messagetype.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindconnect.infrastructure.messagetype.adapters.out.persistence.entity.MessageTypeJpaEntity;

/**
 * Repositorio de Spring Data para message_types. Spring genera la implementación.
 */
public interface MessageTypeJpaRepository extends JpaRepository<MessageTypeJpaEntity, UUID> {
}
