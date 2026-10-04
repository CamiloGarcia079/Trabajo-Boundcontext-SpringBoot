package com.mindconnect.infrastructure.aimodel.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.aimodel.model.aggregate.AiModel;
import com.mindconnect.domain.aimodel.model.valueobject.AiModelId;
import com.mindconnect.domain.aimodel.port.repository.AiModelRepository;
import com.mindconnect.infrastructure.aimodel.adapters.out.persistence.mappers.AiModelPersistenceMapper;

/**
 * Adaptador: implementa el puerto del dominio usando Spring Data y el mapper.
 */
public class AiModelRepositoryAdapter implements AiModelRepository {

    private final AiModelJpaRepository jpaRepository;
    private final AiModelPersistenceMapper mapper;

    public AiModelRepositoryAdapter(AiModelJpaRepository jpaRepository, AiModelPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public AiModel save(AiModel aggregate) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(aggregate)));
    }

    @Override
    public Optional<AiModel> findById(AiModelId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<AiModel> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(AiModel aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
