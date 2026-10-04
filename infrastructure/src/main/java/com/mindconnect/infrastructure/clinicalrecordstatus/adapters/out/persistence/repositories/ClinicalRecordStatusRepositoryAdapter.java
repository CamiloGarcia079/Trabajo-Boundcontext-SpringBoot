package com.mindconnect.infrastructure.clinicalrecordstatus.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.clinicalrecordstatus.model.aggregate.ClinicalRecordStatus;
import com.mindconnect.domain.clinicalrecordstatus.model.valueobject.ClinicalRecordStatusId;
import com.mindconnect.domain.clinicalrecordstatus.port.repository.ClinicalRecordStatusRepository;
import com.mindconnect.infrastructure.clinicalrecordstatus.adapters.out.persistence.mappers.ClinicalRecordStatusPersistenceMapper;

/**
 * Adaptador: implementa el puerto del dominio usando Spring Data y el mapper.
 */
public class ClinicalRecordStatusRepositoryAdapter implements ClinicalRecordStatusRepository {

    private final ClinicalRecordStatusJpaRepository jpaRepository;
    private final ClinicalRecordStatusPersistenceMapper mapper;

    public ClinicalRecordStatusRepositoryAdapter(ClinicalRecordStatusJpaRepository jpaRepository, ClinicalRecordStatusPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ClinicalRecordStatus save(ClinicalRecordStatus aggregate) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(aggregate)));
    }

    @Override
    public Optional<ClinicalRecordStatus> findById(ClinicalRecordStatusId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<ClinicalRecordStatus> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(ClinicalRecordStatus aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
