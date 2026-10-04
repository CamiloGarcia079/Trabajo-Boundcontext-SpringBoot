package com.mindconnect.infrastructure.encounterstatus.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.encounterstatus.model.aggregate.EncounterStatus;
import com.mindconnect.domain.encounterstatus.model.valueobject.EncounterStatusId;
import com.mindconnect.domain.encounterstatus.port.repository.EncounterStatusRepository;
import com.mindconnect.infrastructure.encounterstatus.adapters.out.persistence.mappers.EncounterStatusPersistenceMapper;

/**
 * Adaptador: implementa el puerto del dominio usando Spring Data y el mapper.
 */
public class EncounterStatusRepositoryAdapter implements EncounterStatusRepository {

    private final EncounterStatusJpaRepository jpaRepository;
    private final EncounterStatusPersistenceMapper mapper;

    public EncounterStatusRepositoryAdapter(EncounterStatusJpaRepository jpaRepository, EncounterStatusPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public EncounterStatus save(EncounterStatus aggregate) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(aggregate)));
    }

    @Override
    public Optional<EncounterStatus> findById(EncounterStatusId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<EncounterStatus> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(EncounterStatus aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
