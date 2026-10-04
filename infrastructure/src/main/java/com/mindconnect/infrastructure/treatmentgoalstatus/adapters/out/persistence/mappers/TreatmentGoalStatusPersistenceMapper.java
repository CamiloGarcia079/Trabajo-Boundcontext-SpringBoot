package com.mindconnect.infrastructure.treatmentgoalstatus.adapters.out.persistence.mappers;

import com.mindconnect.domain.treatmentgoalstatus.model.aggregate.TreatmentGoalStatus;
import com.mindconnect.domain.treatmentgoalstatus.model.valueobject.TreatmentGoalStatusId;
import com.mindconnect.infrastructure.treatmentgoalstatus.adapters.out.persistence.entity.TreatmentGoalStatusJpaEntity;

/**
 * Convierte entre el agregado de dominio y la entidad JPA de treatment_goal_statuses.
 */
public class TreatmentGoalStatusPersistenceMapper {

    public TreatmentGoalStatusJpaEntity toJpa(TreatmentGoalStatus domain) {
        if (domain == null) {
            return null;
        }

        TreatmentGoalStatusJpaEntity jpa = new TreatmentGoalStatusJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setCode(domain.code());
        jpa.setName(domain.name());
        jpa.setActive(domain.active());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());

        return jpa;
    }

    public TreatmentGoalStatus toDomain(TreatmentGoalStatusJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }

        return TreatmentGoalStatus.restore(
                new TreatmentGoalStatusId(jpa.getId()),
                jpa.getCode(), jpa.getName(), jpa.getActive(), jpa.getCreatedAt(), jpa.getUpdatedAt());
    }
}
