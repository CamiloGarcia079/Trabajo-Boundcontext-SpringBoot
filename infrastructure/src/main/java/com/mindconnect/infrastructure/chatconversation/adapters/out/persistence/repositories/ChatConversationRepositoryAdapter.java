package com.mindconnect.infrastructure.chatconversation.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.chatconversation.model.aggregate.ChatConversation;
import com.mindconnect.domain.chatconversation.model.valueobject.ChatConversationId;
import com.mindconnect.domain.chatconversation.port.repository.ChatConversationRepository;
import com.mindconnect.infrastructure.chatconversation.adapters.out.persistence.mappers.ChatConversationPersistenceMapper;

/**
 * Adaptador: implementa el puerto del dominio usando Spring Data y el mapper.
 */
public class ChatConversationRepositoryAdapter implements ChatConversationRepository {

    private final ChatConversationJpaRepository jpaRepository;
    private final ChatConversationPersistenceMapper mapper;

    public ChatConversationRepositoryAdapter(ChatConversationJpaRepository jpaRepository, ChatConversationPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ChatConversation save(ChatConversation aggregate) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(aggregate)));
    }

    @Override
    public Optional<ChatConversation> findById(ChatConversationId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<ChatConversation> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(ChatConversation aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
