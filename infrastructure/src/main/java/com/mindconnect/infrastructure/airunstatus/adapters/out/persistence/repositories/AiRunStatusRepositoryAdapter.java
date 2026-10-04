package com.mindconnect.infrastructure.airunstatus.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.airunstatus.model.aggregate.AiRunStatus;
import com.mindconnect.domain.airunstatus.model.valueobject.AiRunStatusId;
import com.mindconnect.domain.airunstatus.port.repository.AiRunStatusRepository;
import com.mindconnect.infrastructure.airunstatus.adapters.out.persistence.mappers.AiRunStatusPersistenceMapper;

/**
 * Adaptador: implementa el puerto del dominio usando Spring Data y el mapper.
 */
public class AiRunStatusRepositoryAdapter implements AiRunStatusRepository {

    private final AiRunStatusJpaRepository jpaRepository;
    private final AiRunStatusPersistenceMapper mapper;

    public AiRunStatusRepositoryAdapter(AiRunStatusJpaRepository jpaRepository, AiRunStatusPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public AiRunStatus save(AiRunStatus aggregate) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(aggregate)));
    }

    @Override
    public Optional<AiRunStatus> findById(AiRunStatusId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<AiRunStatus> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(AiRunStatus aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
