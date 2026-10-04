package com.mindconnect.infrastructure.stateregion.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.stateregion.model.aggregate.StateRegion;
import com.mindconnect.domain.stateregion.model.valueobject.StateRegionId;
import com.mindconnect.domain.stateregion.port.repository.StateRegionRepository;
import com.mindconnect.infrastructure.stateregion.adapters.out.persistence.mappers.StateRegionPersistenceMapper;

/**
 * Adaptador: implementa el puerto del dominio usando Spring Data y el mapper.
 */
public class StateRegionRepositoryAdapter implements StateRegionRepository {

    private final StateRegionJpaRepository jpaRepository;
    private final StateRegionPersistenceMapper mapper;

    public StateRegionRepositoryAdapter(StateRegionJpaRepository jpaRepository, StateRegionPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public StateRegion save(StateRegion aggregate) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(aggregate)));
    }

    @Override
    public Optional<StateRegion> findById(StateRegionId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<StateRegion> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(StateRegion aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
