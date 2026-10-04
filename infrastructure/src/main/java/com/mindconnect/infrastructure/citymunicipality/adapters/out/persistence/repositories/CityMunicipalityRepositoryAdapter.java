package com.mindconnect.infrastructure.citymunicipality.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.citymunicipality.model.aggregate.CityMunicipality;
import com.mindconnect.domain.citymunicipality.model.valueobject.CityMunicipalityId;
import com.mindconnect.domain.citymunicipality.port.repository.CityMunicipalityRepository;
import com.mindconnect.infrastructure.citymunicipality.adapters.out.persistence.mappers.CityMunicipalityPersistenceMapper;

/**
 * Adaptador: implementa el puerto del dominio usando Spring Data y el mapper.
 */
public class CityMunicipalityRepositoryAdapter implements CityMunicipalityRepository {

    private final CityMunicipalityJpaRepository jpaRepository;
    private final CityMunicipalityPersistenceMapper mapper;

    public CityMunicipalityRepositoryAdapter(CityMunicipalityJpaRepository jpaRepository, CityMunicipalityPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public CityMunicipality save(CityMunicipality aggregate) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(aggregate)));
    }

    @Override
    public Optional<CityMunicipality> findById(CityMunicipalityId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<CityMunicipality> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(CityMunicipality aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
