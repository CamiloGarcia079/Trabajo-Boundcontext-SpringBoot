package com.mindconnect.domain.documenttype.port.repository;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.documenttype.model.aggregate.DocumentType;
import com.mindconnect.domain.documenttype.model.valueobject.DocumentTypeId;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y buscar DocumentType.
 * Lo implementa la infraestructura.
 */
public interface DocumentTypeRepository {

    DocumentType save(DocumentType aggregate);

    Optional<DocumentType> findById(DocumentTypeId id);

    List<DocumentType> findAll();

    void delete(DocumentType aggregate);
}
