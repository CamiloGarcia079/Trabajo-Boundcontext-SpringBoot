package com.mindconnect.infrastructure.country.adapters.out.persistence.mappers;

import com.mindconnect.domain.country.model.aggregate.Country;
import com.mindconnect.domain.country.model.valueobject.CountryId;
import com.mindconnect.infrastructure.country.adapters.out.persistence.entity.CountryJpaEntity;

/**
 * Convierte entre el agregado de dominio y la entidad JPA de countries.
 */
public class CountryPersistenceMapper {

    public CountryJpaEntity toJpa(Country domain) {
        if (domain == null) {
            return null;
        }

        CountryJpaEntity jpa = new CountryJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setNameCountry(domain.nameCountry());
        jpa.setCodeCountry(domain.codeCountry());
        jpa.setDescription(domain.description());
        jpa.setIsActive(domain.isActive());
        jpa.setTelephonePrefix(domain.telephonePrefix());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());

        return jpa;
    }

    public Country toDomain(CountryJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }

        return Country.restore(
                new CountryId(jpa.getId()),
                jpa.getNameCountry(), jpa.getCodeCountry(), jpa.getDescription(), jpa.getIsActive(), jpa.getTelephonePrefix(), jpa.getCreatedAt(), jpa.getUpdatedAt());
    }
}
