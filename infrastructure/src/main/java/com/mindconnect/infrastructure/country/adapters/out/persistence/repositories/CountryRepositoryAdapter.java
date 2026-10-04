package com.mindconnect.infrastructure.country.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.country.model.aggregate.Country;
import com.mindconnect.domain.country.model.valueobject.CountryId;
import com.mindconnect.domain.country.port.repository.CountryRepository;
import com.mindconnect.infrastructure.country.adapters.out.persistence.mappers.CountryPersistenceMapper;

/**
 * Adaptador: implementa el puerto del dominio usando Spring Data y el mapper.
 */
public class CountryRepositoryAdapter implements CountryRepository {

    private final CountryJpaRepository jpaRepository;
    private final CountryPersistenceMapper mapper;

    public CountryRepositoryAdapter(CountryJpaRepository jpaRepository, CountryPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Country save(Country aggregate) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(aggregate)));
    }

    @Override
    public Optional<Country> findById(CountryId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<Country> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(Country aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
