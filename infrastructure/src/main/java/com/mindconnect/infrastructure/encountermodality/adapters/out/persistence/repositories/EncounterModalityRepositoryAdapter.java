package com.mindconnect.infrastructure.encountermodality.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.encountermodality.model.aggregate.EncounterModality;
import com.mindconnect.domain.encountermodality.model.valueobject.EncounterModalityId;
import com.mindconnect.domain.encountermodality.port.repository.EncounterModalityRepository;
import com.mindconnect.infrastructure.encountermodality.adapters.out.persistence.mappers.EncounterModalityPersistenceMapper;

/**
 * Adaptador: implementa el puerto del dominio usando Spring Data y el mapper.
 */
public class EncounterModalityRepositoryAdapter implements EncounterModalityRepository {

    private final EncounterModalityJpaRepository jpaRepository;
    private final EncounterModalityPersistenceMapper mapper;

    public EncounterModalityRepositoryAdapter(EncounterModalityJpaRepository jpaRepository, EncounterModalityPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public EncounterModality save(EncounterModality aggregate) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(aggregate)));
    }

    @Override
    public Optional<EncounterModality> findById(EncounterModalityId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<EncounterModality> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(EncounterModality aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
