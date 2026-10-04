package com.mindconnect.domain.diagnosticsystem.port.repository;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.diagnosticsystem.model.aggregate.DiagnosticSystem;
import com.mindconnect.domain.diagnosticsystem.model.valueobject.DiagnosticSystemId;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y buscar DiagnosticSystem.
 * Lo implementa la infraestructura.
 */
public interface DiagnosticSystemRepository {

    DiagnosticSystem save(DiagnosticSystem aggregate);

    Optional<DiagnosticSystem> findById(DiagnosticSystemId id);

    List<DiagnosticSystem> findAll();

    void delete(DiagnosticSystem aggregate);
}
