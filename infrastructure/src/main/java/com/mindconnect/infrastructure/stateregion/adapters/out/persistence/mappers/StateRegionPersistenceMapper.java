package com.mindconnect.infrastructure.stateregion.adapters.out.persistence.mappers;

import com.mindconnect.domain.stateregion.model.aggregate.StateRegion;
import com.mindconnect.domain.stateregion.model.valueobject.StateRegionId;
import com.mindconnect.infrastructure.stateregion.adapters.out.persistence.entity.StateRegionJpaEntity;

/**
 * Convierte entre el agregado de dominio y la entidad JPA de state_regions.
 */
public class StateRegionPersistenceMapper {

    public StateRegionJpaEntity toJpa(StateRegion domain) {
        if (domain == null) {
            return null;
        }

        StateRegionJpaEntity jpa = new StateRegionJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setNameRegion(domain.nameRegion());
        jpa.setCodeRegion(domain.codeRegion());
        jpa.setDescription(domain.description());
        jpa.setIsActive(domain.isActive());
        jpa.setCountryId(domain.countryId());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());

        return jpa;
    }

    public StateRegion toDomain(StateRegionJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }

        return StateRegion.restore(
                new StateRegionId(jpa.getId()),
                jpa.getNameRegion(), jpa.getCodeRegion(), jpa.getDescription(), jpa.getIsActive(), jpa.getCountryId(), jpa.getCreatedAt(), jpa.getUpdatedAt());
    }
}
