package com.mindconnect.domain.escalationstatus.port.repository;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.escalationstatus.model.aggregate.EscalationStatus;
import com.mindconnect.domain.escalationstatus.model.valueobject.EscalationStatusId;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y buscar EscalationStatus.
 * Lo implementa la infraestructura.
 */
public interface EscalationStatusRepository {

    EscalationStatus save(EscalationStatus aggregate);

    Optional<EscalationStatus> findById(EscalationStatusId id);

    List<EscalationStatus> findAll();

    void delete(EscalationStatus aggregate);
}
