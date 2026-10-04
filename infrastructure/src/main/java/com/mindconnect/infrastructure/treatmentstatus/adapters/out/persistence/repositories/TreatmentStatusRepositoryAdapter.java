package com.mindconnect.infrastructure.treatmentstatus.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.treatmentstatus.model.aggregate.TreatmentStatus;
import com.mindconnect.domain.treatmentstatus.model.valueobject.TreatmentStatusId;
import com.mindconnect.domain.treatmentstatus.port.repository.TreatmentStatusRepository;
import com.mindconnect.infrastructure.treatmentstatus.adapters.out.persistence.mappers.TreatmentStatusPersistenceMapper;

/**
 * Adaptador: implementa el puerto del dominio usando Spring Data y el mapper.
 */
public class TreatmentStatusRepositoryAdapter implements TreatmentStatusRepository {

    private final TreatmentStatusJpaRepository jpaRepository;
    private final TreatmentStatusPersistenceMapper mapper;

    public TreatmentStatusRepositoryAdapter(TreatmentStatusJpaRepository jpaRepository, TreatmentStatusPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public TreatmentStatus save(TreatmentStatus aggregate) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(aggregate)));
    }

    @Override
    public Optional<TreatmentStatus> findById(TreatmentStatusId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<TreatmentStatus> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(TreatmentStatus aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
