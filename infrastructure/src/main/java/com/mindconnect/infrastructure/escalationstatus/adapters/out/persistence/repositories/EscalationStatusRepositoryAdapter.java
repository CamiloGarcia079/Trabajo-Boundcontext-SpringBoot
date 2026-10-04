package com.mindconnect.infrastructure.escalationstatus.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.escalationstatus.model.aggregate.EscalationStatus;
import com.mindconnect.domain.escalationstatus.model.valueobject.EscalationStatusId;
import com.mindconnect.domain.escalationstatus.port.repository.EscalationStatusRepository;
import com.mindconnect.infrastructure.escalationstatus.adapters.out.persistence.mappers.EscalationStatusPersistenceMapper;

/**
 * Adaptador: implementa el puerto del dominio usando Spring Data y el mapper.
 */
public class EscalationStatusRepositoryAdapter implements EscalationStatusRepository {

    private final EscalationStatusJpaRepository jpaRepository;
    private final EscalationStatusPersistenceMapper mapper;

    public EscalationStatusRepositoryAdapter(EscalationStatusJpaRepository jpaRepository, EscalationStatusPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public EscalationStatus save(EscalationStatus aggregate) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(aggregate)));
    }

    @Override
    public Optional<EscalationStatus> findById(EscalationStatusId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<EscalationStatus> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(EscalationStatus aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
