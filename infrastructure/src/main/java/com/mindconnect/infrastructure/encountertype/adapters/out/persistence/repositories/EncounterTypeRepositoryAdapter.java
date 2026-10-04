package com.mindconnect.infrastructure.encountertype.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.encountertype.model.aggregate.EncounterType;
import com.mindconnect.domain.encountertype.model.valueobject.EncounterTypeId;
import com.mindconnect.domain.encountertype.port.repository.EncounterTypeRepository;
import com.mindconnect.infrastructure.encountertype.adapters.out.persistence.mappers.EncounterTypePersistenceMapper;

/**
 * Adaptador: implementa el puerto del dominio usando Spring Data y el mapper.
 */
public class EncounterTypeRepositoryAdapter implements EncounterTypeRepository {

    private final EncounterTypeJpaRepository jpaRepository;
    private final EncounterTypePersistenceMapper mapper;

    public EncounterTypeRepositoryAdapter(EncounterTypeJpaRepository jpaRepository, EncounterTypePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public EncounterType save(EncounterType aggregate) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(aggregate)));
    }

    @Override
    public Optional<EncounterType> findById(EncounterTypeId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<EncounterType> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(EncounterType aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
