package com.mindconnect.infrastructure.emailcontact.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindconnect.infrastructure.emailcontact.adapters.out.persistence.entity.EmailContactJpaEntity;

/**
 * Repositorio de Spring Data para email_contacts. Spring genera la implementación.
 */
public interface EmailContactJpaRepository extends JpaRepository<EmailContactJpaEntity, UUID> {
}
