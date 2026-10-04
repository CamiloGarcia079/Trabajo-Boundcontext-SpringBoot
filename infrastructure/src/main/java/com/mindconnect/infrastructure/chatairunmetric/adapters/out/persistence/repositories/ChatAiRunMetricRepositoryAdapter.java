package com.mindconnect.infrastructure.chatairunmetric.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.chatairunmetric.model.aggregate.ChatAiRunMetric;
import com.mindconnect.domain.chatairunmetric.model.valueobject.ChatAiRunMetricId;
import com.mindconnect.domain.chatairunmetric.port.repository.ChatAiRunMetricRepository;
import com.mindconnect.infrastructure.chatairunmetric.adapters.out.persistence.mappers.ChatAiRunMetricPersistenceMapper;

/**
 * Adaptador: implementa el puerto del dominio usando Spring Data y el mapper.
 */
public class ChatAiRunMetricRepositoryAdapter implements ChatAiRunMetricRepository {

    private final ChatAiRunMetricJpaRepository jpaRepository;
    private final ChatAiRunMetricPersistenceMapper mapper;

    public ChatAiRunMetricRepositoryAdapter(ChatAiRunMetricJpaRepository jpaRepository, ChatAiRunMetricPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ChatAiRunMetric save(ChatAiRunMetric aggregate) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(aggregate)));
    }

    @Override
    public Optional<ChatAiRunMetric> findById(ChatAiRunMetricId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<ChatAiRunMetric> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(ChatAiRunMetric aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
