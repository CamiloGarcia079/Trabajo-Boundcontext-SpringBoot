package com.mindconnect.domain.stateregion.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import com.mindconnect.domain.common.model.AggregateRoot;
import com.mindconnect.domain.common.validation.DomainValidations;
import com.mindconnect.domain.stateregion.event.StateRegionRegisteredEvent;
import com.mindconnect.domain.stateregion.event.StateRegionUpdatedEvent;
import com.mindconnect.domain.stateregion.model.valueobject.StateRegionId;

/**
 * Agregado raíz del contexto stateregion: representa la tabla state_regions.
 *
 * <p>Relaciones (se guardan solo por id, no como objetos):</p>
 * <ul>
 *   <li>countryId -> Country</li>
 * </ul>
 *
 * <p>El id y las fechas los pone el propio agregado. No usa Spring ni JPA.</p>
 */
public class StateRegion extends AggregateRoot {

    private final StateRegionId id;
    private String nameRegion;
    private String codeRegion;
    private String description;
    private boolean isActive;
    private UUID countryId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private StateRegion(
            StateRegionId id,
            String nameRegion,
            String codeRegion,
            String description,
            boolean isActive,
            UUID countryId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        DomainValidations.required(nameRegion, "nameRegion");
        DomainValidations.required(codeRegion, "codeRegion");
        DomainValidations.required(description, "description");
        DomainValidations.required(countryId, "countryId");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(updatedAt, "updatedAt");
        this.nameRegion = nameRegion;
        this.codeRegion = codeRegion;
        this.description = description;
        this.isActive = isActive;
        this.countryId = countryId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /** Crea un registro nuevo: genera el id y las fechas, y deja el evento de registro. */
    public static StateRegion register(
            String nameRegion,
            String codeRegion,
            String description,
            UUID countryId) {
        StateRegionId id = StateRegionId.generate();
        LocalDateTime now = LocalDateTime.now();
        StateRegion aggregate = new StateRegion(id, nameRegion, codeRegion, description, true, countryId, now, now);
        aggregate.recordEvent(new StateRegionRegisteredEvent(id, now));
        return aggregate;
    }

    /** Reconstruye un registro que ya existía (viene de la base de datos). No genera eventos. */
    public static StateRegion restore(
            StateRegionId id,
            String nameRegion,
            String codeRegion,
            String description,
            boolean isActive,
            UUID countryId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new StateRegion(id, nameRegion, codeRegion, description, isActive, countryId, createdAt, updatedAt);
    }

    /** Cambia los datos del registro, actualiza la fecha de modificación y deja el evento. */
    public void update(
            String nameRegion,
            String codeRegion,
            String description,
            UUID countryId) {
        DomainValidations.required(nameRegion, "nameRegion");
        DomainValidations.required(codeRegion, "codeRegion");
        DomainValidations.required(description, "description");
        DomainValidations.required(countryId, "countryId");
        this.nameRegion = nameRegion;
        this.codeRegion = codeRegion;
        this.description = description;
        this.countryId = countryId;
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new StateRegionUpdatedEvent(this.id, now));
    }

    public StateRegionId id() { return id; }
    public String nameRegion() { return nameRegion; }
    public String codeRegion() { return codeRegion; }
    public String description() { return description; }
    public boolean isActive() { return isActive; }
    public UUID countryId() { return countryId; }
    public LocalDateTime createdAt() { return createdAt; }
    public LocalDateTime updatedAt() { return updatedAt; }
}
