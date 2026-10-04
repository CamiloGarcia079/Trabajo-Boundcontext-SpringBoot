package com.mindconnect.infrastructure.chatescalationstatushistory.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.chatescalationstatushistory.model.aggregate.ChatEscalationStatusHistory;
import com.mindconnect.domain.chatescalationstatushistory.model.valueobject.ChatEscalationStatusHistoryId;
import com.mindconnect.domain.chatescalationstatushistory.port.repository.ChatEscalationStatusHistoryRepository;
import com.mindconnect.infrastructure.chatescalationstatushistory.adapters.out.persistence.mappers.ChatEscalationStatusHistoryPersistenceMapper;

/**
 * Adaptador: implementa el puerto del dominio usando Spring Data y el mapper.
 */
public class ChatEscalationStatusHistoryRepositoryAdapter implements ChatEscalationStatusHistoryRepository {

    private final ChatEscalationStatusHistoryJpaRepository jpaRepository;
    private final ChatEscalationStatusHistoryPersistenceMapper mapper;

    public ChatEscalationStatusHistoryRepositoryAdapter(ChatEscalationStatusHistoryJpaRepository jpaRepository, ChatEscalationStatusHistoryPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ChatEscalationStatusHistory save(ChatEscalationStatusHistory aggregate) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(aggregate)));
    }

    @Override
    public Optional<ChatEscalationStatusHistory> findById(ChatEscalationStatusHistoryId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<ChatEscalationStatusHistory> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(ChatEscalationStatusHistory aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
