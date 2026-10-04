package com.mindconnect.infrastructure.providermodelai.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.providermodelai.model.aggregate.ProviderModelAi;
import com.mindconnect.domain.providermodelai.model.valueobject.ProviderModelAiId;
import com.mindconnect.domain.providermodelai.port.repository.ProviderModelAiRepository;
import com.mindconnect.infrastructure.providermodelai.adapters.out.persistence.mappers.ProviderModelAiPersistenceMapper;

/**
 * Adaptador: implementa el puerto del dominio usando Spring Data y el mapper.
 */
public class ProviderModelAiRepositoryAdapter implements ProviderModelAiRepository {

    private final ProviderModelAiJpaRepository jpaRepository;
    private final ProviderModelAiPersistenceMapper mapper;

    public ProviderModelAiRepositoryAdapter(ProviderModelAiJpaRepository jpaRepository, ProviderModelAiPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ProviderModelAi save(ProviderModelAi aggregate) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(aggregate)));
    }

    @Override
    public Optional<ProviderModelAi> findById(ProviderModelAiId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<ProviderModelAi> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(ProviderModelAi aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
