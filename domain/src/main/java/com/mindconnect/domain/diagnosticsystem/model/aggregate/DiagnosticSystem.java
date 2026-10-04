package com.mindconnect.domain.diagnosticsystem.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.mindconnect.domain.common.model.AggregateRoot;
import com.mindconnect.domain.common.validation.DomainValidations;
import com.mindconnect.domain.diagnosticsystem.event.DiagnosticSystemRegisteredEvent;
import com.mindconnect.domain.diagnosticsystem.event.DiagnosticSystemUpdatedEvent;
import com.mindconnect.domain.diagnosticsystem.model.valueobject.DiagnosticSystemId;

/**
 * Agregado raíz del contexto diagnosticsystem: representa la tabla diagnostic_systems.
 *
 * <p>El id y las fechas los pone el propio agregado. No usa Spring ni JPA.</p>
 */
public class DiagnosticSystem extends AggregateRoot {

    private final DiagnosticSystemId id;
    private String code;
    private String name;
    private boolean active;
    private String version;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private DiagnosticSystem(
            DiagnosticSystemId id,
            String code,
            String name,
            boolean active,
            String version,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        DomainValidations.required(code, "code");
        DomainValidations.required(name, "name");
        DomainValidations.required(version, "version");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(updatedAt, "updatedAt");
        this.code = code;
        this.name = name;
        this.active = active;
        this.version = version;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /** Crea un registro nuevo: genera el id y las fechas, y deja el evento de registro. */
    public static DiagnosticSystem register(
            String code,
            String name,
            String version) {
        DiagnosticSystemId id = DiagnosticSystemId.generate();
        LocalDateTime now = LocalDateTime.now();
        DiagnosticSystem aggregate = new DiagnosticSystem(id, code, name, true, version, now, now);
        aggregate.recordEvent(new DiagnosticSystemRegisteredEvent(id, now));
        return aggregate;
    }

    /** Reconstruye un registro que ya existía (viene de la base de datos). No genera eventos. */
    public static DiagnosticSystem restore(
            DiagnosticSystemId id,
            String code,
            String name,
            boolean active,
            String version,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new DiagnosticSystem(id, code, name, active, version, createdAt, updatedAt);
    }

    /** Cambia los datos del registro, actualiza la fecha de modificación y deja el evento. */
    public void update(
            String code,
            String name,
            String version) {
        DomainValidations.required(code, "code");
        DomainValidations.required(name, "name");
        DomainValidations.required(version, "version");
        this.code = code;
        this.name = name;
        this.version = version;
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new DiagnosticSystemUpdatedEvent(this.id, now));
    }

    public DiagnosticSystemId id() { return id; }
    public String code() { return code; }
    public String name() { return name; }
    public boolean active() { return active; }
    public String version() { return version; }
    public LocalDateTime createdAt() { return createdAt; }
    public LocalDateTime updatedAt() { return updatedAt; }
}
