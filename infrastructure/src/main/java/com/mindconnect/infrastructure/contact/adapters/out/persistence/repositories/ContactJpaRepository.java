package com.mindconnect.infrastructure.contact.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindconnect.infrastructure.contact.adapters.out.persistence.entity.ContactJpaEntity;

/**
 * Repositorio de Spring Data para contacts. Spring genera la implementación.
 */
public interface ContactJpaRepository extends JpaRepository<ContactJpaEntity, UUID> {
}
