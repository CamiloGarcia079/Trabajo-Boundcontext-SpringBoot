package com.mindconnect.infrastructure.escalationstatus.adapters.out.persistence.mappers;

import com.mindconnect.domain.escalationstatus.model.aggregate.EscalationStatus;
import com.mindconnect.domain.escalationstatus.model.valueobject.EscalationStatusId;
import com.mindconnect.infrastructure.escalationstatus.adapters.out.persistence.entity.EscalationStatusJpaEntity;

/**
 * Convierte entre el agregado de dominio y la entidad JPA de escalations_statuses.
 */
public class EscalationStatusPersistenceMapper {

    public EscalationStatusJpaEntity toJpa(EscalationStatus domain) {
        if (domain == null) {
            return null;
        }

        EscalationStatusJpaEntity jpa = new EscalationStatusJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setNameStatus(domain.nameStatus());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());

        return jpa;
    }

    public EscalationStatus toDomain(EscalationStatusJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }

        return EscalationStatus.restore(
                new EscalationStatusId(jpa.getId()),
                jpa.getNameStatus(), jpa.getCreatedAt(), jpa.getUpdatedAt());
    }
}
