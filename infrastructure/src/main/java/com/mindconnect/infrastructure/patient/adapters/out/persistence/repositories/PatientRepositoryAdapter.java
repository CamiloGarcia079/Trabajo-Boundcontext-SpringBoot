package com.mindconnect.infrastructure.patient.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.patient.model.aggregate.Patient;
import com.mindconnect.domain.patient.model.valueobject.PatientId;
import com.mindconnect.domain.patient.port.repository.PatientRepository;
import com.mindconnect.infrastructure.patient.adapters.out.persistence.mappers.PatientPersistenceMapper;

/**
 * Adaptador: implementa el puerto del dominio usando Spring Data y el mapper.
 */
public class PatientRepositoryAdapter implements PatientRepository {

    private final PatientJpaRepository jpaRepository;
    private final PatientPersistenceMapper mapper;

    public PatientRepositoryAdapter(PatientJpaRepository jpaRepository, PatientPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Patient save(Patient aggregate) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(aggregate)));
    }

    @Override
    public Optional<Patient> findById(PatientId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<Patient> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(Patient aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
