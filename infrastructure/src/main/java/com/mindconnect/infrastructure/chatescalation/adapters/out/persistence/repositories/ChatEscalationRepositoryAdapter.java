package com.mindconnect.infrastructure.chatescalation.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.chatescalation.model.aggregate.ChatEscalation;
import com.mindconnect.domain.chatescalation.model.valueobject.ChatEscalationId;
import com.mindconnect.domain.chatescalation.port.repository.ChatEscalationRepository;
import com.mindconnect.infrastructure.chatescalation.adapters.out.persistence.mappers.ChatEscalationPersistenceMapper;

/**
 * Adaptador: implementa el puerto del dominio usando Spring Data y el mapper.
 */
public class ChatEscalationRepositoryAdapter implements ChatEscalationRepository {

    private final ChatEscalationJpaRepository jpaRepository;
    private final ChatEscalationPersistenceMapper mapper;

    public ChatEscalationRepositoryAdapter(ChatEscalationJpaRepository jpaRepository, ChatEscalationPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ChatEscalation save(ChatEscalation aggregate) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(aggregate)));
    }

    @Override
    public Optional<ChatEscalation> findById(ChatEscalationId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<ChatEscalation> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(ChatEscalation aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
