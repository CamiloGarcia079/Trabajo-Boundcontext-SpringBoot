package com.mindconnect.infrastructure.emailcontact.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.emailcontact.model.aggregate.EmailContact;
import com.mindconnect.domain.emailcontact.model.valueobject.EmailContactId;
import com.mindconnect.domain.emailcontact.port.repository.EmailContactRepository;
import com.mindconnect.infrastructure.emailcontact.adapters.out.persistence.mappers.EmailContactPersistenceMapper;

/**
 * Adaptador: implementa el puerto del dominio usando Spring Data y el mapper.
 */
public class EmailContactRepositoryAdapter implements EmailContactRepository {

    private final EmailContactJpaRepository jpaRepository;
    private final EmailContactPersistenceMapper mapper;

    public EmailContactRepositoryAdapter(EmailContactJpaRepository jpaRepository, EmailContactPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public EmailContact save(EmailContact aggregate) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(aggregate)));
    }

    @Override
    public Optional<EmailContact> findById(EmailContactId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<EmailContact> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(EmailContact aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
