package com.mindconnect.application.diagnosticsystem.usecase;

import com.mindconnect.application.diagnosticsystem.command.UpdateDiagnosticSystemCommand;
import com.mindconnect.application.diagnosticsystem.dto.DiagnosticSystemResponse;
import com.mindconnect.application.diagnosticsystem.exception.DiagnosticSystemNotFoundApplicationException;
import com.mindconnect.domain.diagnosticsystem.model.aggregate.DiagnosticSystem;
import com.mindconnect.domain.diagnosticsystem.port.repository.DiagnosticSystemRepository;

public class UpdateDiagnosticSystemUseCase {

    private final DiagnosticSystemRepository repository;

    public UpdateDiagnosticSystemUseCase(DiagnosticSystemRepository repository) {
        this.repository = repository;
    }

    public DiagnosticSystemResponse execute(UpdateDiagnosticSystemCommand command) {
        DiagnosticSystem aggregate = repository.findById(command.id())
                .orElseThrow(() -> new DiagnosticSystemNotFoundApplicationException(command.id()));
        aggregate.update(
                command.code(), command.name(), command.version());
        DiagnosticSystem saved = repository.save(aggregate);
        return DiagnosticSystemResponse.fromDomain(saved);
    }
}
