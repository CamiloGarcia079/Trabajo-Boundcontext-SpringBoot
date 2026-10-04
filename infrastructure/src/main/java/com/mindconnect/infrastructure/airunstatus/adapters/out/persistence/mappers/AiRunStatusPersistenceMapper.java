package com.mindconnect.infrastructure.airunstatus.adapters.out.persistence.mappers;

import com.mindconnect.domain.airunstatus.model.aggregate.AiRunStatus;
import com.mindconnect.domain.airunstatus.model.valueobject.AiRunStatusId;
import com.mindconnect.infrastructure.airunstatus.adapters.out.persistence.entity.AiRunStatusJpaEntity;

/**
 * Convierte entre el agregado de dominio y la entidad JPA de ai_runs_statuses.
 */
public class AiRunStatusPersistenceMapper {

    public AiRunStatusJpaEntity toJpa(AiRunStatus domain) {
        if (domain == null) {
            return null;
        }

        AiRunStatusJpaEntity jpa = new AiRunStatusJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setNameStatus(domain.nameStatus());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());

        return jpa;
    }

    public AiRunStatus toDomain(AiRunStatusJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }

        return AiRunStatus.restore(
                new AiRunStatusId(jpa.getId()),
                jpa.getNameStatus(), jpa.getCreatedAt(), jpa.getUpdatedAt());
    }
}
