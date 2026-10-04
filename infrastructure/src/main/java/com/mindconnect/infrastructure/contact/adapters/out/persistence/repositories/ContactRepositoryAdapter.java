package com.mindconnect.infrastructure.contact.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.contact.model.aggregate.Contact;
import com.mindconnect.domain.contact.model.valueobject.ContactId;
import com.mindconnect.domain.contact.port.repository.ContactRepository;
import com.mindconnect.infrastructure.contact.adapters.out.persistence.mappers.ContactPersistenceMapper;

/**
 * Adaptador: implementa el puerto del dominio usando Spring Data y el mapper.
 */
public class ContactRepositoryAdapter implements ContactRepository {

    private final ContactJpaRepository jpaRepository;
    private final ContactPersistenceMapper mapper;

    public ContactRepositoryAdapter(ContactJpaRepository jpaRepository, ContactPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Contact save(Contact aggregate) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(aggregate)));
    }

    @Override
    public Optional<Contact> findById(ContactId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<Contact> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(Contact aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
