package com.mindconnect.infrastructure.consenttype.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.consenttype.model.aggregate.ConsentType;
import com.mindconnect.domain.consenttype.model.valueobject.ConsentTypeId;
import com.mindconnect.domain.consenttype.port.repository.ConsentTypeRepository;
import com.mindconnect.infrastructure.consenttype.adapters.out.persistence.mappers.ConsentTypePersistenceMapper;

/**
 * Adaptador: implementa el puerto del dominio usando Spring Data y el mapper.
 */
public class ConsentTypeRepositoryAdapter implements ConsentTypeRepository {

    private final ConsentTypeJpaRepository jpaRepository;
    private final ConsentTypePersistenceMapper mapper;

    public ConsentTypeRepositoryAdapter(ConsentTypeJpaRepository jpaRepository, ConsentTypePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ConsentType save(ConsentType aggregate) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(aggregate)));
    }

    @Override
    public Optional<ConsentType> findById(ConsentTypeId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<ConsentType> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(ConsentType aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
