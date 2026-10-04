package com.mindconnect.infrastructure.mentalstatusexam.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindconnect.infrastructure.mentalstatusexam.adapters.out.persistence.entity.MentalStatusExamJpaEntity;

/**
 * Repositorio de Spring Data para mental_status_exams. Spring genera la implementación.
 */
public interface MentalStatusExamJpaRepository extends JpaRepository<MentalStatusExamJpaEntity, UUID> {
}
