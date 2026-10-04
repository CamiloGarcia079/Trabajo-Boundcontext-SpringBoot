package com.mindconnect.infrastructure.medicationroute.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.medicationroute.model.aggregate.MedicationRoute;
import com.mindconnect.domain.medicationroute.model.valueobject.MedicationRouteId;
import com.mindconnect.domain.medicationroute.port.repository.MedicationRouteRepository;
import com.mindconnect.infrastructure.medicationroute.adapters.out.persistence.mappers.MedicationRoutePersistenceMapper;

/**
 * Adaptador: implementa el puerto del dominio usando Spring Data y el mapper.
 */
public class MedicationRouteRepositoryAdapter implements MedicationRouteRepository {

    private final MedicationRouteJpaRepository jpaRepository;
    private final MedicationRoutePersistenceMapper mapper;

    public MedicationRouteRepositoryAdapter(MedicationRouteJpaRepository jpaRepository, MedicationRoutePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public MedicationRoute save(MedicationRoute aggregate) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(aggregate)));
    }

    @Override
    public Optional<MedicationRoute> findById(MedicationRouteId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<MedicationRoute> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(MedicationRoute aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
