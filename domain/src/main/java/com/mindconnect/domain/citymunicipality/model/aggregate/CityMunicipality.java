package com.mindconnect.domain.citymunicipality.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import com.mindconnect.domain.common.model.AggregateRoot;
import com.mindconnect.domain.common.validation.DomainValidations;
import com.mindconnect.domain.citymunicipality.event.CityMunicipalityRegisteredEvent;
import com.mindconnect.domain.citymunicipality.event.CityMunicipalityUpdatedEvent;
import com.mindconnect.domain.citymunicipality.model.valueobject.CityMunicipalityId;

/**
 * Agregado raíz del contexto citymunicipality: representa la tabla city_municipalities.
 *
 * <p>Relaciones (se guardan solo por id, no como objetos):</p>
 * <ul>
 *   <li>regionId -> StateRegion</li>
 * </ul>
 *
 * <p>El id y las fechas los pone el propio agregado. No usa Spring ni JPA.</p>
 */
public class CityMunicipality extends AggregateRoot {

    private final CityMunicipalityId id;
    private String nameCity;
    private String codeCity;
    private String description;
    private boolean isActive;
    private UUID regionId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private CityMunicipality(
            CityMunicipalityId id,
            String nameCity,
            String codeCity,
            String description,
            boolean isActive,
            UUID regionId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        DomainValidations.required(nameCity, "nameCity");
        DomainValidations.required(codeCity, "codeCity");
        DomainValidations.required(description, "description");
        DomainValidations.required(regionId, "regionId");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(updatedAt, "updatedAt");
        this.nameCity = nameCity;
        this.codeCity = codeCity;
        this.description = description;
        this.isActive = isActive;
        this.regionId = regionId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /** Crea un registro nuevo: genera el id y las fechas, y deja el evento de registro. */
    public static CityMunicipality register(
            String nameCity,
            String codeCity,
            String description,
            UUID regionId) {
        CityMunicipalityId id = CityMunicipalityId.generate();
        LocalDateTime now = LocalDateTime.now();
        CityMunicipality aggregate = new CityMunicipality(id, nameCity, codeCity, description, true, regionId, now, now);
        aggregate.recordEvent(new CityMunicipalityRegisteredEvent(id, now));
        return aggregate;
    }

    /** Reconstruye un registro que ya existía (viene de la base de datos). No genera eventos. */
    public static CityMunicipality restore(
            CityMunicipalityId id,
            String nameCity,
            String codeCity,
            String description,
            boolean isActive,
            UUID regionId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new CityMunicipality(id, nameCity, codeCity, description, isActive, regionId, createdAt, updatedAt);
    }

    /** Cambia los datos del registro, actualiza la fecha de modificación y deja el evento. */
    public void update(
            String nameCity,
            String codeCity,
            String description,
            UUID regionId) {
        DomainValidations.required(nameCity, "nameCity");
        DomainValidations.required(codeCity, "codeCity");
        DomainValidations.required(description, "description");
        DomainValidations.required(regionId, "regionId");
        this.nameCity = nameCity;
        this.codeCity = codeCity;
        this.description = description;
        this.regionId = regionId;
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new CityMunicipalityUpdatedEvent(this.id, now));
    }

    public CityMunicipalityId id() { return id; }
    public String nameCity() { return nameCity; }
    public String codeCity() { return codeCity; }
    public String description() { return description; }
    public boolean isActive() { return isActive; }
    public UUID regionId() { return regionId; }
    public LocalDateTime createdAt() { return createdAt; }
    public LocalDateTime updatedAt() { return updatedAt; }
}
