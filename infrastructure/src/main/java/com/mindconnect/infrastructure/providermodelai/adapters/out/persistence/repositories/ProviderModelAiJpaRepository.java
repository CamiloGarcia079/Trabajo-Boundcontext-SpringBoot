package com.mindconnect.infrastructure.providermodelai.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindconnect.infrastructure.providermodelai.adapters.out.persistence.entity.ProviderModelAiJpaEntity;

/**
 * Repositorio de Spring Data para provider_models_ai. Spring genera la implementación.
 */
public interface ProviderModelAiJpaRepository extends JpaRepository<ProviderModelAiJpaEntity, UUID> {
}
