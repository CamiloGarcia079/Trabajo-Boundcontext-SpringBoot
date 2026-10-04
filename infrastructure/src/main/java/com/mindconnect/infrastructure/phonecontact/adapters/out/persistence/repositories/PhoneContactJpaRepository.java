package com.mindconnect.infrastructure.phonecontact.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindconnect.infrastructure.phonecontact.adapters.out.persistence.entity.PhoneContactJpaEntity;

/**
 * Repositorio de Spring Data para phone_contacts. Spring genera la implementación.
 */
public interface PhoneContactJpaRepository extends JpaRepository<PhoneContactJpaEntity, UUID> {
}
