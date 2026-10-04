package com.mindconnect.infrastructure.sendertype.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindconnect.infrastructure.sendertype.adapters.out.persistence.entity.SenderTypeJpaEntity;

/**
 * Repositorio de Spring Data para sender_types. Spring genera la implementación.
 */
public interface SenderTypeJpaRepository extends JpaRepository<SenderTypeJpaEntity, UUID> {
}
