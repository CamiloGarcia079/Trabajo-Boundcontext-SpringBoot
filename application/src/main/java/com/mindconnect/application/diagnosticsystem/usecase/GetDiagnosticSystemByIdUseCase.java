package com.mindconnect.application.diagnosticsystem.usecase;

import com.mindconnect.application.diagnosticsystem.dto.DiagnosticSystemResponse;
import com.mindconnect.application.diagnosticsystem.exception.DiagnosticSystemNotFoundApplicationException;
import com.mindconnect.domain.diagnosticsystem.model.valueobject.DiagnosticSystemId;
import com.mindconnect.domain.diagnosticsystem.port.repository.DiagnosticSystemRepository;

public class GetDiagnosticSystemByIdUseCase {

    private final DiagnosticSystemRepository repository;

    public GetDiagnosticSystemByIdUseCase(DiagnosticSystemRepository repository) {
        this.repository = repository;
    }

    public DiagnosticSystemResponse execute(DiagnosticSystemId id) {
        return repository.findById(id)
                .map(DiagnosticSystemResponse::fromDomain)
                .orElseThrow(() -> new DiagnosticSystemNotFoundApplicationException(id));
    }
}
