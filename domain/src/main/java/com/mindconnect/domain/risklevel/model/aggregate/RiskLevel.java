package com.mindconnect.domain.risklevel.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.mindconnect.domain.common.model.AggregateRoot;
import com.mindconnect.domain.common.validation.DomainValidations;
import com.mindconnect.domain.risklevel.event.RiskLevelRegisteredEvent;
import com.mindconnect.domain.risklevel.event.RiskLevelUpdatedEvent;
import com.mindconnect.domain.risklevel.model.valueobject.RiskLevelId;

/**
 * Agregado raíz del contexto risklevel: representa la tabla risk_levels.
 *
 * <p>El id y las fechas los pone el propio agregado. No usa Spring ni JPA.</p>
 */
public class RiskLevel extends AggregateRoot {

    private final RiskLevelId id;
    private String code;
    private String name;
    private boolean active;
    private Integer severity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private RiskLevel(
            RiskLevelId id,
            String code,
            String name,
            boolean active,
            Integer severity,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        DomainValidations.required(code, "code");
        DomainValidations.required(name, "name");
        DomainValidations.required(severity, "severity");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(updatedAt, "updatedAt");
        this.code = code;
        this.name = name;
        this.active = active;
        this.severity = severity;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /** Crea un registro nuevo: genera el id y las fechas, y deja el evento de registro. */
    public static RiskLevel register(
            String code,
            String name,
            Integer severity) {
        RiskLevelId id = RiskLevelId.generate();
        LocalDateTime now = LocalDateTime.now();
        RiskLevel aggregate = new RiskLevel(id, code, name, true, severity, now, now);
        aggregate.recordEvent(new RiskLevelRegisteredEvent(id, now));
        return aggregate;
    }

    /** Reconstruye un registro que ya existía (viene de la base de datos). No genera eventos. */
    public static RiskLevel restore(
            RiskLevelId id,
            String code,
            String name,
            boolean active,
            Integer severity,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new RiskLevel(id, code, name, active, severity, createdAt, updatedAt);
    }

    /** Cambia los datos del registro, actualiza la fecha de modificación y deja el evento. */
    public void update(
            String code,
            String name,
            Integer severity) {
        DomainValidations.required(code, "code");
        DomainValidations.required(name, "name");
        DomainValidations.required(severity, "severity");
        this.code = code;
        this.name = name;
        this.severity = severity;
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new RiskLevelUpdatedEvent(this.id, now));
    }

    public RiskLevelId id() { return id; }
    public String code() { return code; }
    public String name() { return name; }
    public boolean active() { return active; }
    public Integer severity() { return severity; }
    public LocalDateTime createdAt() { return createdAt; }
    public LocalDateTime updatedAt() { return updatedAt; }
}
