package com.mindconnect.infrastructure.assessmenttype.adapters.out.persistence.mappers;

import com.mindconnect.domain.assessmenttype.model.aggregate.AssessmentType;
import com.mindconnect.domain.assessmenttype.model.valueobject.AssessmentTypeId;
import com.mindconnect.infrastructure.assessmenttype.adapters.out.persistence.entity.AssessmentTypeJpaEntity;

/**
 * Convierte entre el agregado de dominio y la entidad JPA de assessment_types.
 */
public class AssessmentTypePersistenceMapper {

    public AssessmentTypeJpaEntity toJpa(AssessmentType domain) {
        if (domain == null) {
            return null;
        }

        AssessmentTypeJpaEntity jpa = new AssessmentTypeJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setCode(domain.code());
        jpa.setName(domain.name());
        jpa.setActive(domain.active());
        jpa.setDescription(domain.description());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());

        return jpa;
    }

    public AssessmentType toDomain(AssessmentTypeJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }

        return AssessmentType.restore(
                new AssessmentTypeId(jpa.getId()),
                jpa.getCode(), jpa.getName(), jpa.getActive(), jpa.getDescription(), jpa.getCreatedAt(), jpa.getUpdatedAt());
    }
}
