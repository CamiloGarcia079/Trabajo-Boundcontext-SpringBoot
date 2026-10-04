package com.mindconnect.domain.contact.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import com.mindconnect.domain.common.model.AggregateRoot;
import com.mindconnect.domain.common.validation.DomainValidations;
import com.mindconnect.domain.contact.event.ContactRegisteredEvent;
import com.mindconnect.domain.contact.event.ContactUpdatedEvent;
import com.mindconnect.domain.contact.model.valueobject.ContactId;

/**
 * Agregado raíz del contexto contact: representa la tabla contacts.
 *
 * <p>Relaciones (se guardan solo por id, no como objetos):</p>
 * <ul>
 *   <li>cityId -> CityMunicipality</li>
 *   <li>createdBy -> Professional</li>
 *   <li>updatedBy -> Professional</li>
 * </ul>
 *
 * <p>El id y las fechas los pone el propio agregado. No usa Spring ni JPA.</p>
 */
public class Contact extends AggregateRoot {

    private final ContactId id;
    private String fullName;
    private String email;
    private String notes;
    private UUID cityId;
    private LocalDateTime createdAt;
    private UUID createdBy;
    private LocalDateTime updatedAt;
    private UUID updatedBy;

    private Contact(
            ContactId id,
            String fullName,
            String email,
            String notes,
            UUID cityId,
            LocalDateTime createdAt,
            UUID createdBy,
            LocalDateTime updatedAt,
            UUID updatedBy) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        DomainValidations.required(fullName, "fullName");
        DomainValidations.required(email, "email");
        DomainValidations.required(notes, "notes");
        DomainValidations.required(cityId, "cityId");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(createdBy, "createdBy");
        DomainValidations.required(updatedAt, "updatedAt");
        this.fullName = fullName;
        this.email = email;
        this.notes = notes;
        this.cityId = cityId;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
    }

    /** Crea un registro nuevo: genera el id y las fechas, y deja el evento de registro. */
    public static Contact register(
            String fullName,
            String email,
            String notes,
            UUID cityId,
            UUID createdBy,
            UUID updatedBy) {
        ContactId id = ContactId.generate();
        LocalDateTime now = LocalDateTime.now();
        Contact aggregate = new Contact(id, fullName, email, notes, cityId, now, createdBy, now, updatedBy);
        aggregate.recordEvent(new ContactRegisteredEvent(id, now));
        return aggregate;
    }

    /** Reconstruye un registro que ya existía (viene de la base de datos). No genera eventos. */
    public static Contact restore(
            ContactId id,
            String fullName,
            String email,
            String notes,
            UUID cityId,
            LocalDateTime createdAt,
            UUID createdBy,
            LocalDateTime updatedAt,
            UUID updatedBy) {
        return new Contact(id, fullName, email, notes, cityId, createdAt, createdBy, updatedAt, updatedBy);
    }

    /** Cambia los datos del registro, actualiza la fecha de modificación y deja el evento. */
    public void update(
            String fullName,
            String email,
            String notes,
            UUID cityId,
            UUID updatedBy) {
        DomainValidations.required(fullName, "fullName");
        DomainValidations.required(email, "email");
        DomainValidations.required(notes, "notes");
        DomainValidations.required(cityId, "cityId");
        this.fullName = fullName;
        this.email = email;
        this.notes = notes;
        this.cityId = cityId;
        this.updatedBy = updatedBy;
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new ContactUpdatedEvent(this.id, now));
    }

    public ContactId id() { return id; }
    public String fullName() { return fullName; }
    public String email() { return email; }
    public String notes() { return notes; }
    public UUID cityId() { return cityId; }
    public LocalDateTime createdAt() { return createdAt; }
    public UUID createdBy() { return createdBy; }
    public LocalDateTime updatedAt() { return updatedAt; }
    public UUID updatedBy() { return updatedBy; }
}
