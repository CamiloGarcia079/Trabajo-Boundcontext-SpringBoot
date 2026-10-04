package com.mindconnect.infrastructure.study.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindconnect.infrastructure.study.adapters.out.persistence.entity.StudyJpaEntity;

/**
 * Repositorio de Spring Data para studies. Spring genera la implementación.
 */
public interface StudyJpaRepository extends JpaRepository<StudyJpaEntity, UUID> {
}
