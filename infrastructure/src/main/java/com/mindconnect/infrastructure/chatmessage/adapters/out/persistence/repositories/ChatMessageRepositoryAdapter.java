package com.mindconnect.infrastructure.chatmessage.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.chatmessage.model.aggregate.ChatMessage;
import com.mindconnect.domain.chatmessage.model.valueobject.ChatMessageId;
import com.mindconnect.domain.chatmessage.port.repository.ChatMessageRepository;
import com.mindconnect.infrastructure.chatmessage.adapters.out.persistence.mappers.ChatMessagePersistenceMapper;

/**
 * Adaptador: implementa el puerto del dominio usando Spring Data y el mapper.
 */
public class ChatMessageRepositoryAdapter implements ChatMessageRepository {

    private final ChatMessageJpaRepository jpaRepository;
    private final ChatMessagePersistenceMapper mapper;

    public ChatMessageRepositoryAdapter(ChatMessageJpaRepository jpaRepository, ChatMessagePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ChatMessage save(ChatMessage aggregate) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(aggregate)));
    }

    @Override
    public Optional<ChatMessage> findById(ChatMessageId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<ChatMessage> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(ChatMessage aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
