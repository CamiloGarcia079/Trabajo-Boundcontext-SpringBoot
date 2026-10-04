package com.mindconnect.infrastructure.professionaltype.adapters.out.persistence.mappers;

import com.mindconnect.domain.professionaltype.model.aggregate.ProfessionalType;
import com.mindconnect.domain.professionaltype.model.valueobject.ProfessionalTypeId;
import com.mindconnect.infrastructure.professionaltype.adapters.out.persistence.entity.ProfessionalTypeJpaEntity;

/**
 * Convierte entre el agregado de dominio y la entidad JPA de professional_types.
 */
public class ProfessionalTypePersistenceMapper {

    public ProfessionalTypeJpaEntity toJpa(ProfessionalType domain) {
        if (domain == null) {
            return null;
        }

        ProfessionalTypeJpaEntity jpa = new ProfessionalTypeJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setName(domain.name());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());

        return jpa;
    }

    public ProfessionalType toDomain(ProfessionalTypeJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }

        return ProfessionalType.restore(
                new ProfessionalTypeId(jpa.getId()),
                jpa.getName(), jpa.getCreatedAt(), jpa.getUpdatedAt());
    }
}
