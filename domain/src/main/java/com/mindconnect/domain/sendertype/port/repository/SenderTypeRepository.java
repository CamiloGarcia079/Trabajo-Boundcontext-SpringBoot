package com.mindconnect.domain.sendertype.port.repository;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.sendertype.model.aggregate.SenderType;
import com.mindconnect.domain.sendertype.model.valueobject.SenderTypeId;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y buscar SenderType.
 * Lo implementa la infraestructura.
 */
public interface SenderTypeRepository {

    SenderType save(SenderType aggregate);

    Optional<SenderType> findById(SenderTypeId id);

    List<SenderType> findAll();

    void delete(SenderType aggregate);
}
