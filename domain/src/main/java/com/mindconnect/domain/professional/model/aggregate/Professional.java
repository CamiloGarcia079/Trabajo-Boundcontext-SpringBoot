package com.mindconnect.domain.professional.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import com.mindconnect.domain.common.model.AggregateRoot;
import com.mindconnect.domain.common.validation.DomainValidations;
import com.mindconnect.domain.professional.event.ProfessionalRegisteredEvent;
import com.mindconnect.domain.professional.event.ProfessionalUpdatedEvent;
import com.mindconnect.domain.professional.model.valueobject.ProfessionalId;

/**
 * Agregado raíz del contexto professional: representa la tabla professionals.
 *
 * <p>Relaciones (se guardan solo por id, no como objetos):</p>
 * <ul>
 *   <li>documentTypeId -> DocumentType</li>
 *   <li>professionalType -> ProfessionalType</li>
 *   <li>cityId -> CityMunicipality</li>
 * </ul>
 *
 * <p>El id y las fechas los pone el propio agregado. No usa Spring ni JPA.</p>
 */
public class Professional extends AggregateRoot {

    private final ProfessionalId id;
    private UUID documentTypeId;
    private String documentNumber;
    private String firstName;
    private String lastName;
    private UUID professionalType;
    private String licenseNumber;
    private boolean active;
    private UUID cityId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Professional(
            ProfessionalId id,
            UUID documentTypeId,
            String documentNumber,
            String firstName,
            String lastName,
            UUID professionalType,
            String licenseNumber,
            boolean active,
            UUID cityId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        DomainValidations.required(documentTypeId, "documentTypeId");
        DomainValidations.required(documentNumber, "documentNumber");
        DomainValidations.required(firstName, "firstName");
        DomainValidations.required(lastName, "lastName");
        DomainValidations.required(professionalType, "professionalType");
        DomainValidations.required(licenseNumber, "licenseNumber");
        DomainValidations.required(cityId, "cityId");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(updatedAt, "updatedAt");
        this.documentTypeId = documentTypeId;
        this.documentNumber = documentNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.professionalType = professionalType;
        this.licenseNumber = licenseNumber;
        this.active = active;
        this.cityId = cityId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /** Crea un registro nuevo: genera el id y las fechas, y deja el evento de registro. */
    public static Professional register(
            UUID documentTypeId,
            String documentNumber,
            String firstName,
            String lastName,
            UUID professionalType,
            String licenseNumber,
            UUID cityId) {
        ProfessionalId id = ProfessionalId.generate();
        LocalDateTime now = LocalDateTime.now();
        Professional aggregate = new Professional(id, documentTypeId, documentNumber, firstName, lastName, professionalType, licenseNumber, true, cityId, now, now);
        aggregate.recordEvent(new ProfessionalRegisteredEvent(id, now));
        return aggregate;
    }

    /** Reconstruye un registro que ya existía (viene de la base de datos). No genera eventos. */
    public static Professional restore(
            ProfessionalId id,
            UUID documentTypeId,
            String documentNumber,
            String firstName,
            String lastName,
            UUID professionalType,
            String licenseNumber,
            boolean active,
            UUID cityId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new Professional(id, documentTypeId, documentNumber, firstName, lastName, professionalType, licenseNumber, active, cityId, createdAt, updatedAt);
    }

    /** Cambia los datos del registro, actualiza la fecha de modificación y deja el evento. */
    public void update(
            UUID documentTypeId,
            String documentNumber,
            String firstName,
            String lastName,
            UUID professionalType,
            String licenseNumber,
            UUID cityId) {
        DomainValidations.required(documentTypeId, "documentTypeId");
        DomainValidations.required(documentNumber, "documentNumber");
        DomainValidations.required(firstName, "firstName");
        DomainValidations.required(lastName, "lastName");
        DomainValidations.required(professionalType, "professionalType");
        DomainValidations.required(licenseNumber, "licenseNumber");
        DomainValidations.required(cityId, "cityId");
        this.documentTypeId = documentTypeId;
        this.documentNumber = documentNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.professionalType = professionalType;
        this.licenseNumber = licenseNumber;
        this.cityId = cityId;
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new ProfessionalUpdatedEvent(this.id, now));
    }

    public ProfessionalId id() { return id; }
    public UUID documentTypeId() { return documentTypeId; }
    public String documentNumber() { return documentNumber; }
    public String firstName() { return firstName; }
    public String lastName() { return lastName; }
    public UUID professionalType() { return professionalType; }
    public String licenseNumber() { return licenseNumber; }
    public boolean active() { return active; }
    public UUID cityId() { return cityId; }
    public LocalDateTime createdAt() { return createdAt; }
    public LocalDateTime updatedAt() { return updatedAt; }
}
