package com.mindconnect.infrastructure.conversationstatus.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.conversationstatus.model.aggregate.ConversationStatus;
import com.mindconnect.domain.conversationstatus.model.valueobject.ConversationStatusId;
import com.mindconnect.domain.conversationstatus.port.repository.ConversationStatusRepository;
import com.mindconnect.infrastructure.conversationstatus.adapters.out.persistence.mappers.ConversationStatusPersistenceMapper;

/**
 * Adaptador: implementa el puerto del dominio usando Spring Data y el mapper.
 */
public class ConversationStatusRepositoryAdapter implements ConversationStatusRepository {

    private final ConversationStatusJpaRepository jpaRepository;
    private final ConversationStatusPersistenceMapper mapper;

    public ConversationStatusRepositoryAdapter(ConversationStatusJpaRepository jpaRepository, ConversationStatusPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ConversationStatus save(ConversationStatus aggregate) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(aggregate)));
    }

    @Override
    public Optional<ConversationStatus> findById(ConversationStatusId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<ConversationStatus> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(ConversationStatus aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
