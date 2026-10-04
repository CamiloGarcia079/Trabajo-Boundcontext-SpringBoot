package com.mindconnect.infrastructure.encountertype.adapters.out.persistence.mappers;

import com.mindconnect.domain.encountertype.model.aggregate.EncounterType;
import com.mindconnect.domain.encountertype.model.valueobject.EncounterTypeId;
import com.mindconnect.infrastructure.encountertype.adapters.out.persistence.entity.EncounterTypeJpaEntity;

/**
 * Convierte entre el agregado de dominio y la entidad JPA de encounter_types.
 */
public class EncounterTypePersistenceMapper {

    public EncounterTypeJpaEntity toJpa(EncounterType domain) {
        if (domain == null) {
            return null;
        }

        EncounterTypeJpaEntity jpa = new EncounterTypeJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setCode(domain.code());
        jpa.setName(domain.name());
        jpa.setActive(domain.active());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());

        return jpa;
    }

    public EncounterType toDomain(EncounterTypeJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }

        return EncounterType.restore(
                new EncounterTypeId(jpa.getId()),
                jpa.getCode(), jpa.getName(), jpa.getActive(), jpa.getCreatedAt(), jpa.getUpdatedAt());
    }
}
