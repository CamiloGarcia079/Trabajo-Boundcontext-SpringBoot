package com.mindconnect.infrastructure.clinicalnote.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.clinicalnote.model.aggregate.ClinicalNote;
import com.mindconnect.domain.clinicalnote.model.valueobject.ClinicalNoteId;
import com.mindconnect.domain.clinicalnote.port.repository.ClinicalNoteRepository;
import com.mindconnect.infrastructure.clinicalnote.adapters.out.persistence.mappers.ClinicalNotePersistenceMapper;

/**
 * Adaptador: implementa el puerto del dominio usando Spring Data y el mapper.
 */
public class ClinicalNoteRepositoryAdapter implements ClinicalNoteRepository {

    private final ClinicalNoteJpaRepository jpaRepository;
    private final ClinicalNotePersistenceMapper mapper;

    public ClinicalNoteRepositoryAdapter(ClinicalNoteJpaRepository jpaRepository, ClinicalNotePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ClinicalNote save(ClinicalNote aggregate) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(aggregate)));
    }

    @Override
    public Optional<ClinicalNote> findById(ClinicalNoteId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<ClinicalNote> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(ClinicalNote aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
