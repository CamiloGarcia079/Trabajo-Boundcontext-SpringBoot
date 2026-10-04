package com.mindconnect.infrastructure.patientallergy.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.patientallergy.model.aggregate.PatientAllergy;
import com.mindconnect.domain.patientallergy.model.valueobject.PatientAllergyId;
import com.mindconnect.domain.patientallergy.port.repository.PatientAllergyRepository;
import com.mindconnect.infrastructure.patientallergy.adapters.out.persistence.mappers.PatientAllergyPersistenceMapper;

/**
 * Adaptador: implementa el puerto del dominio usando Spring Data y el mapper.
 */
public class PatientAllergyRepositoryAdapter implements PatientAllergyRepository {

    private final PatientAllergyJpaRepository jpaRepository;
    private final PatientAllergyPersistenceMapper mapper;

    public PatientAllergyRepositoryAdapter(PatientAllergyJpaRepository jpaRepository, PatientAllergyPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public PatientAllergy save(PatientAllergy aggregate) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(aggregate)));
    }

    @Override
    public Optional<PatientAllergy> findById(PatientAllergyId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<PatientAllergy> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(PatientAllergy aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
