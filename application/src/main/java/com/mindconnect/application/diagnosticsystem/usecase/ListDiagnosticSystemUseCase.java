package com.mindconnect.application.diagnosticsystem.usecase;

import java.util.List;

import com.mindconnect.application.diagnosticsystem.dto.DiagnosticSystemResponse;
import com.mindconnect.domain.diagnosticsystem.port.repository.DiagnosticSystemRepository;

public class ListDiagnosticSystemUseCase {

    private final DiagnosticSystemRepository repository;

    public ListDiagnosticSystemUseCase(DiagnosticSystemRepository repository) {
        this.repository = repository;
    }

    public List<DiagnosticSystemResponse> execute() {
        return repository.findAll()
                .stream()
                .map(DiagnosticSystemResponse::fromDomain)
                .toList();
    }
}
