package com.mindconnect.infrastructure.documenttype.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.documenttype.model.aggregate.DocumentType;
import com.mindconnect.domain.documenttype.model.valueobject.DocumentTypeId;
import com.mindconnect.domain.documenttype.port.repository.DocumentTypeRepository;
import com.mindconnect.infrastructure.documenttype.adapters.out.persistence.mappers.DocumentTypePersistenceMapper;

/**
 * Adaptador: implementa el puerto del dominio usando Spring Data y el mapper.
 */
public class DocumentTypeRepositoryAdapter implements DocumentTypeRepository {

    private final DocumentTypeJpaRepository jpaRepository;
    private final DocumentTypePersistenceMapper mapper;

    public DocumentTypeRepositoryAdapter(DocumentTypeJpaRepository jpaRepository, DocumentTypePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public DocumentType save(DocumentType aggregate) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(aggregate)));
    }

    @Override
    public Optional<DocumentType> findById(DocumentTypeId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<DocumentType> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(DocumentType aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
