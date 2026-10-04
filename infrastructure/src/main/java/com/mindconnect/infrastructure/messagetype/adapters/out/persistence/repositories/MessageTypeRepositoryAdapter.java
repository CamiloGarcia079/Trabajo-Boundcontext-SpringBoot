package com.mindconnect.infrastructure.messagetype.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.messagetype.model.aggregate.MessageType;
import com.mindconnect.domain.messagetype.model.valueobject.MessageTypeId;
import com.mindconnect.domain.messagetype.port.repository.MessageTypeRepository;
import com.mindconnect.infrastructure.messagetype.adapters.out.persistence.mappers.MessageTypePersistenceMapper;

/**
 * Adaptador: implementa el puerto del dominio usando Spring Data y el mapper.
 */
public class MessageTypeRepositoryAdapter implements MessageTypeRepository {

    private final MessageTypeJpaRepository jpaRepository;
    private final MessageTypePersistenceMapper mapper;

    public MessageTypeRepositoryAdapter(MessageTypeJpaRepository jpaRepository, MessageTypePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public MessageType save(MessageType aggregate) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(aggregate)));
    }

    @Override
    public Optional<MessageType> findById(MessageTypeId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<MessageType> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(MessageType aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
