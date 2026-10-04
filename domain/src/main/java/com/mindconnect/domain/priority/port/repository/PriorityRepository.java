package com.mindconnect.domain.priority.port.repository;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.priority.model.aggregate.Priority;
import com.mindconnect.domain.priority.model.valueobject.PriorityId;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y buscar Priority.
 * Lo implementa la infraestructura.
 */
public interface PriorityRepository {

    Priority save(Priority aggregate);

    Optional<Priority> findById(PriorityId id);

    List<Priority> findAll();

    void delete(Priority aggregate);
}
