package com.mindconnect.infrastructure.patientcontact.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.patientcontact.model.aggregate.PatientContact;
import com.mindconnect.domain.patientcontact.model.valueobject.PatientContactId;
import com.mindconnect.domain.patientcontact.port.repository.PatientContactRepository;
import com.mindconnect.infrastructure.patientcontact.adapters.out.persistence.mappers.PatientContactPersistenceMapper;

/**
 * Adaptador: implementa el puerto del dominio usando Spring Data y el mapper.
 */
public class PatientContactRepositoryAdapter implements PatientContactRepository {

    private final PatientContactJpaRepository jpaRepository;
    private final PatientContactPersistenceMapper mapper;

    public PatientContactRepositoryAdapter(PatientContactJpaRepository jpaRepository, PatientContactPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public PatientContact save(PatientContact aggregate) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(aggregate)));
    }

    @Override
    public Optional<PatientContact> findById(PatientContactId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<PatientContact> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(PatientContact aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
