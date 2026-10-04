package com.mindconnect.domain.treatmentgoalstatus.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.mindconnect.domain.common.model.AggregateRoot;
import com.mindconnect.domain.common.validation.DomainValidations;
import com.mindconnect.domain.treatmentgoalstatus.event.TreatmentGoalStatusRegisteredEvent;
import com.mindconnect.domain.treatmentgoalstatus.event.TreatmentGoalStatusUpdatedEvent;
import com.mindconnect.domain.treatmentgoalstatus.model.valueobject.TreatmentGoalStatusId;

/**
 * Agregado raíz del contexto treatmentgoalstatus: representa la tabla treatment_goal_statuses.
 *
 * <p>El id y las fechas los pone el propio agregado. No usa Spring ni JPA.</p>
 */
public class TreatmentGoalStatus extends AggregateRoot {

    private final TreatmentGoalStatusId id;
    private String code;
    private String name;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private TreatmentGoalStatus(
            TreatmentGoalStatusId id,
            String code,
            String name,
            boolean active,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        DomainValidations.required(code, "code");
        DomainValidations.required(name, "name");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(updatedAt, "updatedAt");
        this.code = code;
        this.name = name;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /** Crea un registro nuevo: genera el id y las fechas, y deja el evento de registro. */
    public static TreatmentGoalStatus register(
            String code,
            String name) {
        TreatmentGoalStatusId id = TreatmentGoalStatusId.generate();
        LocalDateTime now = LocalDateTime.now();
        TreatmentGoalStatus aggregate = new TreatmentGoalStatus(id, code, name, true, now, now);
        aggregate.recordEvent(new TreatmentGoalStatusRegisteredEvent(id, now));
        return aggregate;
    }

    /** Reconstruye un registro que ya existía (viene de la base de datos). No genera eventos. */
    public static TreatmentGoalStatus restore(
            TreatmentGoalStatusId id,
            String code,
            String name,
            boolean active,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new TreatmentGoalStatus(id, code, name, active, createdAt, updatedAt);
    }

    /** Cambia los datos del registro, actualiza la fecha de modificación y deja el evento. */
    public void update(
            String code,
            String name) {
        DomainValidations.required(code, "code");
        DomainValidations.required(name, "name");
        this.code = code;
        this.name = name;
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new TreatmentGoalStatusUpdatedEvent(this.id, now));
    }

    public TreatmentGoalStatusId id() { return id; }
    public String code() { return code; }
    public String name() { return name; }
    public boolean active() { return active; }
    public LocalDateTime createdAt() { return createdAt; }
    public LocalDateTime updatedAt() { return updatedAt; }
}
