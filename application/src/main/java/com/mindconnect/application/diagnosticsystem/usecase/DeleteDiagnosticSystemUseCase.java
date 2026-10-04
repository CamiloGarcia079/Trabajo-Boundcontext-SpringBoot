package com.mindconnect.application.diagnosticsystem.usecase;

import java.time.LocalDateTime;

import com.mindconnect.application.diagnosticsystem.exception.DiagnosticSystemNotFoundApplicationException;
import com.mindconnect.domain.diagnosticsystem.event.DiagnosticSystemDeletedEvent;
import com.mindconnect.domain.diagnosticsystem.model.valueobject.DiagnosticSystemId;
import com.mindconnect.domain.diagnosticsystem.port.repository.DiagnosticSystemRepository;

public class DeleteDiagnosticSystemUseCase {

    private final DiagnosticSystemRepository repository;

    public DeleteDiagnosticSystemUseCase(DiagnosticSystemRepository repository) {
        this.repository = repository;
    }

    public DiagnosticSystemDeletedEvent execute(DiagnosticSystemId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new DiagnosticSystemNotFoundApplicationException(id));

        repository.delete(aggregate);

        return new DiagnosticSystemDeletedEvent(id, LocalDateTime.now());
    }
}
