package com.mindconnect.domain.country.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.mindconnect.domain.common.model.AggregateRoot;
import com.mindconnect.domain.common.validation.DomainValidations;
import com.mindconnect.domain.country.event.CountryRegisteredEvent;
import com.mindconnect.domain.country.event.CountryUpdatedEvent;
import com.mindconnect.domain.country.model.valueobject.CountryId;

/**
 * Agregado raíz del contexto country: representa la tabla countries.
 *
 * <p>El id y las fechas los pone el propio agregado. No usa Spring ni JPA.</p>
 */
public class Country extends AggregateRoot {

    private final CountryId id;
    private String nameCountry;
    private String codeCountry;
    private String description;
    private boolean isActive;
    private String telephonePrefix;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Country(
            CountryId id,
            String nameCountry,
            String codeCountry,
            String description,
            boolean isActive,
            String telephonePrefix,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        DomainValidations.required(nameCountry, "nameCountry");
        DomainValidations.required(codeCountry, "codeCountry");
        DomainValidations.required(description, "description");
        DomainValidations.required(telephonePrefix, "telephonePrefix");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(updatedAt, "updatedAt");
        this.nameCountry = nameCountry;
        this.codeCountry = codeCountry;
        this.description = description;
        this.isActive = isActive;
        this.telephonePrefix = telephonePrefix;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /** Crea un registro nuevo: genera el id y las fechas, y deja el evento de registro. */
    public static Country register(
            String nameCountry,
            String codeCountry,
            String description,
            String telephonePrefix) {
        CountryId id = CountryId.generate();
        LocalDateTime now = LocalDateTime.now();
        Country aggregate = new Country(id, nameCountry, codeCountry, description, true, telephonePrefix, now, now);
        aggregate.recordEvent(new CountryRegisteredEvent(id, now));
        return aggregate;
    }

    /** Reconstruye un registro que ya existía (viene de la base de datos). No genera eventos. */
    public static Country restore(
            CountryId id,
            String nameCountry,
            String codeCountry,
            String description,
            boolean isActive,
            String telephonePrefix,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new Country(id, nameCountry, codeCountry, description, isActive, telephonePrefix, createdAt, updatedAt);
    }

    /** Cambia los datos del registro, actualiza la fecha de modificación y deja el evento. */
    public void update(
            String nameCountry,
            String codeCountry,
            String description,
            String telephonePrefix) {
        DomainValidations.required(nameCountry, "nameCountry");
        DomainValidations.required(codeCountry, "codeCountry");
        DomainValidations.required(description, "description");
        DomainValidations.required(telephonePrefix, "telephonePrefix");
        this.nameCountry = nameCountry;
        this.codeCountry = codeCountry;
        this.description = description;
        this.telephonePrefix = telephonePrefix;
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new CountryUpdatedEvent(this.id, now));
    }

    public CountryId id() { return id; }
    public String nameCountry() { return nameCountry; }
    public String codeCountry() { return codeCountry; }
    public String description() { return description; }
    public boolean isActive() { return isActive; }
    public String telephonePrefix() { return telephonePrefix; }
    public LocalDateTime createdAt() { return createdAt; }
    public LocalDateTime updatedAt() { return updatedAt; }
}
