package com.mindconnect.application.diagnosticsystem.command;


public record RegisterDiagnosticSystemCommand(
        String code,
        String name,
        String version
) {
}
