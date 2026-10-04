package com.mindconnect.infrastructure.priority.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.priority.model.aggregate.Priority;
import com.mindconnect.domain.priority.model.valueobject.PriorityId;
import com.mindconnect.domain.priority.port.repository.PriorityRepository;
import com.mindconnect.infrastructure.priority.adapters.out.persistence.mappers.PriorityPersistenceMapper;

/**
 * Adaptador: implementa el puerto del dominio usando Spring Data y el mapper.
 */
public class PriorityRepositoryAdapter implements PriorityRepository {

    private final PriorityJpaRepository jpaRepository;
    private final PriorityPersistenceMapper mapper;

    public PriorityRepositoryAdapter(PriorityJpaRepository jpaRepository, PriorityPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Priority save(Priority aggregate) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(aggregate)));
    }

    @Override
    public Optional<Priority> findById(PriorityId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<Priority> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(Priority aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
