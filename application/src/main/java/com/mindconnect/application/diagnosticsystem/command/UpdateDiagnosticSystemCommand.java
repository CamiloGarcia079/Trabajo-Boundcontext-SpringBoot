package com.mindconnect.application.diagnosticsystem.command;


import com.mindconnect.domain.diagnosticsystem.model.valueobject.DiagnosticSystemId;

public record UpdateDiagnosticSystemCommand(
        DiagnosticSystemId id,
        String code,
        String name,
        String version
) {
}
