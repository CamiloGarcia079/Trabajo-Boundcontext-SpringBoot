package com.mindconnect.infrastructure.treatmentstatus.adapters.out.persistence.mappers;

import com.mindconnect.domain.treatmentstatus.model.aggregate.TreatmentStatus;
import com.mindconnect.domain.treatmentstatus.model.valueobject.TreatmentStatusId;
import com.mindconnect.infrastructure.treatmentstatus.adapters.out.persistence.entity.TreatmentStatusJpaEntity;

/**
 * Convierte entre el agregado de dominio y la entidad JPA de treatment_statuses.
 */
public class TreatmentStatusPersistenceMapper {

    public TreatmentStatusJpaEntity toJpa(TreatmentStatus domain) {
        if (domain == null) {
            return null;
        }

        TreatmentStatusJpaEntity jpa = new TreatmentStatusJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setCode(domain.code());
        jpa.setName(domain.name());
        jpa.setActive(domain.active());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());

        return jpa;
    }

    public TreatmentStatus toDomain(TreatmentStatusJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }

        return TreatmentStatus.restore(
                new TreatmentStatusId(jpa.getId()),
                jpa.getCode(), jpa.getName(), jpa.getActive(), jpa.getCreatedAt(), jpa.getUpdatedAt());
    }
}
