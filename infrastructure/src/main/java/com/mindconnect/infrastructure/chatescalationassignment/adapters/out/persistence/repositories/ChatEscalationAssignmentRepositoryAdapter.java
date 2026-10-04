package com.mindconnect.infrastructure.chatescalationassignment.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.chatescalationassignment.model.aggregate.ChatEscalationAssignment;
import com.mindconnect.domain.chatescalationassignment.model.valueobject.ChatEscalationAssignmentId;
import com.mindconnect.domain.chatescalationassignment.port.repository.ChatEscalationAssignmentRepository;
import com.mindconnect.infrastructure.chatescalationassignment.adapters.out.persistence.mappers.ChatEscalationAssignmentPersistenceMapper;

/**
 * Adaptador: implementa el puerto del dominio usando Spring Data y el mapper.
 */
public class ChatEscalationAssignmentRepositoryAdapter implements ChatEscalationAssignmentRepository {

    private final ChatEscalationAssignmentJpaRepository jpaRepository;
    private final ChatEscalationAssignmentPersistenceMapper mapper;

    public ChatEscalationAssignmentRepositoryAdapter(ChatEscalationAssignmentJpaRepository jpaRepository, ChatEscalationAssignmentPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ChatEscalationAssignment save(ChatEscalationAssignment aggregate) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(aggregate)));
    }

    @Override
    public Optional<ChatEscalationAssignment> findById(ChatEscalationAssignmentId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<ChatEscalationAssignment> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(ChatEscalationAssignment aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
