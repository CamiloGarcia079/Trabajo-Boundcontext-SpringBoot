package com.mindconnect.infrastructure.citymunicipality.adapters.out.persistence.mappers;

import com.mindconnect.domain.citymunicipality.model.aggregate.CityMunicipality;
import com.mindconnect.domain.citymunicipality.model.valueobject.CityMunicipalityId;
import com.mindconnect.infrastructure.citymunicipality.adapters.out.persistence.entity.CityMunicipalityJpaEntity;

/**
 * Convierte entre el agregado de dominio y la entidad JPA de city_municipalities.
 */
public class CityMunicipalityPersistenceMapper {

    public CityMunicipalityJpaEntity toJpa(CityMunicipality domain) {
        if (domain == null) {
            return null;
        }

        CityMunicipalityJpaEntity jpa = new CityMunicipalityJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setNameCity(domain.nameCity());
        jpa.setCodeCity(domain.codeCity());
        jpa.setDescription(domain.description());
        jpa.setIsActive(domain.isActive());
        jpa.setRegionId(domain.regionId());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());

        return jpa;
    }

    public CityMunicipality toDomain(CityMunicipalityJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }

        return CityMunicipality.restore(
                new CityMunicipalityId(jpa.getId()),
                jpa.getNameCity(), jpa.getCodeCity(), jpa.getDescription(), jpa.getIsActive(), jpa.getRegionId(), jpa.getCreatedAt(), jpa.getUpdatedAt());
    }
}
