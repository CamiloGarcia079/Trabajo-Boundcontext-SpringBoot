package com.mindconnect.infrastructure.chatairunerror.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.chatairunerror.model.aggregate.ChatAiRunError;
import com.mindconnect.domain.chatairunerror.model.valueobject.ChatAiRunErrorId;
import com.mindconnect.domain.chatairunerror.port.repository.ChatAiRunErrorRepository;
import com.mindconnect.infrastructure.chatairunerror.adapters.out.persistence.mappers.ChatAiRunErrorPersistenceMapper;

/**
 * Adaptador: implementa el puerto del dominio usando Spring Data y el mapper.
 */
public class ChatAiRunErrorRepositoryAdapter implements ChatAiRunErrorRepository {

    private final ChatAiRunErrorJpaRepository jpaRepository;
    private final ChatAiRunErrorPersistenceMapper mapper;

    public ChatAiRunErrorRepositoryAdapter(ChatAiRunErrorJpaRepository jpaRepository, ChatAiRunErrorPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ChatAiRunError save(ChatAiRunError aggregate) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(aggregate)));
    }

    @Override
    public Optional<ChatAiRunError> findById(ChatAiRunErrorId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<ChatAiRunError> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(ChatAiRunError aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
