package com.mindconnect.infrastructure.consenttype.adapters.out.persistence.mappers;

import com.mindconnect.domain.consenttype.model.aggregate.ConsentType;
import com.mindconnect.domain.consenttype.model.valueobject.ConsentTypeId;
import com.mindconnect.infrastructure.consenttype.adapters.out.persistence.entity.ConsentTypeJpaEntity;

/**
 * Convierte entre el agregado de dominio y la entidad JPA de consent_types.
 */
public class ConsentTypePersistenceMapper {

    public ConsentTypeJpaEntity toJpa(ConsentType domain) {
        if (domain == null) {
            return null;
        }

        ConsentTypeJpaEntity jpa = new ConsentTypeJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setCode(domain.code());
        jpa.setName(domain.name());
        jpa.setActive(domain.active());
        jpa.setDescription(domain.description());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());

        return jpa;
    }

    public ConsentType toDomain(ConsentTypeJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }

        return ConsentType.restore(
                new ConsentTypeId(jpa.getId()),
                jpa.getCode(), jpa.getName(), jpa.getActive(), jpa.getDescription(), jpa.getCreatedAt(), jpa.getUpdatedAt());
    }
}
