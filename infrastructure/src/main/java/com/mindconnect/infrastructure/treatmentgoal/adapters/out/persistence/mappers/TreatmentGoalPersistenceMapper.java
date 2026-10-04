package com.mindconnect.infrastructure.treatmentgoal.adapters.out.persistence.mappers;

import com.mindconnect.domain.treatmentgoal.model.aggregate.TreatmentGoal;
import com.mindconnect.domain.treatmentgoal.model.valueobject.TreatmentGoalId;
import com.mindconnect.infrastructure.treatmentgoal.adapters.out.persistence.entity.TreatmentGoalJpaEntity;

/**
 * Convierte entre el agregado de dominio y la entidad JPA de treatment_goals.
 */
public class TreatmentGoalPersistenceMapper {

    public TreatmentGoalJpaEntity toJpa(TreatmentGoal domain) {
        if (domain == null) {
            return null;
        }

        TreatmentGoalJpaEntity jpa = new TreatmentGoalJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setTreatmentPlanId(domain.treatmentPlanId());
        jpa.setDescription(domain.description());
        jpa.setTargetDate(domain.targetDate());
        jpa.setCompletedAt(domain.completedAt());
        jpa.setNotes(domain.notes());
        jpa.setTreatmentGoalId(domain.treatmentGoalId());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());

        return jpa;
    }

    public TreatmentGoal toDomain(TreatmentGoalJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }

        return TreatmentGoal.restore(
                new TreatmentGoalId(jpa.getId()),
                jpa.getTreatmentPlanId(), jpa.getDescription(), jpa.getTargetDate(), jpa.getCompletedAt(), jpa.getNotes(), jpa.getTreatmentGoalId(), jpa.getCreatedAt(), jpa.getUpdatedAt());
    }
}
