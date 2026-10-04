package com.mindconnect.application.diagnosticsystem.usecase;

import com.mindconnect.application.diagnosticsystem.command.RegisterDiagnosticSystemCommand;
import com.mindconnect.application.diagnosticsystem.dto.DiagnosticSystemResponse;
import com.mindconnect.domain.diagnosticsystem.model.aggregate.DiagnosticSystem;
import com.mindconnect.domain.diagnosticsystem.port.repository.DiagnosticSystemRepository;

public class RegisterDiagnosticSystemUseCase {

    private final DiagnosticSystemRepository repository;

    public RegisterDiagnosticSystemUseCase(DiagnosticSystemRepository repository) {
        this.repository = repository;
    }

    public DiagnosticSystemResponse execute(RegisterDiagnosticSystemCommand command) {
        DiagnosticSystem aggregate = DiagnosticSystem.register(
                command.code(), command.name(), command.version());
        DiagnosticSystem saved = repository.save(aggregate);
        return DiagnosticSystemResponse.fromDomain(saved);
    }
}
