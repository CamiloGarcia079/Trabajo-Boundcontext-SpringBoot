package com.mindconnect.infrastructure.phonecontact.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.phonecontact.model.aggregate.PhoneContact;
import com.mindconnect.domain.phonecontact.model.valueobject.PhoneContactId;
import com.mindconnect.domain.phonecontact.port.repository.PhoneContactRepository;
import com.mindconnect.infrastructure.phonecontact.adapters.out.persistence.mappers.PhoneContactPersistenceMapper;

/**
 * Adaptador: implementa el puerto del dominio usando Spring Data y el mapper.
 */
public class PhoneContactRepositoryAdapter implements PhoneContactRepository {

    private final PhoneContactJpaRepository jpaRepository;
    private final PhoneContactPersistenceMapper mapper;

    public PhoneContactRepositoryAdapter(PhoneContactJpaRepository jpaRepository, PhoneContactPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public PhoneContact save(PhoneContact aggregate) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(aggregate)));
    }

    @Override
    public Optional<PhoneContact> findById(PhoneContactId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<PhoneContact> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(PhoneContact aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
