package com.mindconnect.infrastructure.encounter.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.encounter.model.aggregate.Encounter;
import com.mindconnect.domain.encounter.model.valueobject.EncounterId;
import com.mindconnect.domain.encounter.port.repository.EncounterRepository;
import com.mindconnect.infrastructure.encounter.adapters.out.persistence.mappers.EncounterPersistenceMapper;

/**
 * Adaptador: implementa el puerto del dominio usando Spring Data y el mapper.
 */
public class EncounterRepositoryAdapter implements EncounterRepository {

    private final EncounterJpaRepository jpaRepository;
    private final EncounterPersistenceMapper mapper;

    public EncounterRepositoryAdapter(EncounterJpaRepository jpaRepository, EncounterPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Encounter save(Encounter aggregate) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(aggregate)));
    }

    @Override
    public Optional<Encounter> findById(EncounterId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<Encounter> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(Encounter aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
