package com.mindconnect.domain.patient.model.aggregate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import com.mindconnect.domain.common.model.AggregateRoot;
import com.mindconnect.domain.common.validation.DomainValidations;
import com.mindconnect.domain.patient.event.PatientRegisteredEvent;
import com.mindconnect.domain.patient.event.PatientUpdatedEvent;
import com.mindconnect.domain.patient.model.valueobject.PatientId;

/**
 * Agregado raíz del contexto patient: representa la tabla patients.
 *
 * <p>Relaciones (se guardan solo por id, no como objetos):</p>
 * <ul>
 *   <li>documentTypeId -> DocumentType</li>
 *   <li>biologicalSexId -> Gender</li>
 *   <li>genderIdentity -> Gender</li>
 *   <li>createdBy -> Professional</li>
 *   <li>updatedBy -> Professional</li>
 *   <li>cityId -> CityMunicipality</li>
 * </ul>
 *
 * <p>El id y las fechas los pone el propio agregado. No usa Spring ni JPA.</p>
 */
public class Patient extends AggregateRoot {

    private final PatientId id;
    private UUID documentTypeId;
    private String documentNumber;
    private String firstName;
    private String middleName;
    private String lastName;
    private String secondLastName;
    private LocalDate birthDate;
    private UUID biologicalSexId;
    private UUID genderIdentity;
    private String email;
    private String phone;
    private String address;
    private boolean active;
    private LocalDateTime createdAt;
    private UUID createdBy;
    private LocalDateTime updatedAt;
    private UUID updatedBy;
    private UUID cityId;

    private Patient(
            PatientId id,
            UUID documentTypeId,
            String documentNumber,
            String firstName,
            String middleName,
            String lastName,
            String secondLastName,
            LocalDate birthDate,
            UUID biologicalSexId,
            UUID genderIdentity,
            String email,
            String phone,
            String address,
            boolean active,
            LocalDateTime createdAt,
            UUID createdBy,
            LocalDateTime updatedAt,
            UUID updatedBy,
            UUID cityId) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        DomainValidations.required(documentTypeId, "documentTypeId");
        DomainValidations.required(documentNumber, "documentNumber");
        DomainValidations.required(firstName, "firstName");
        DomainValidations.required(lastName, "lastName");
        DomainValidations.required(birthDate, "birthDate");
        DomainValidations.required(biologicalSexId, "biologicalSexId");
        DomainValidations.required(genderIdentity, "genderIdentity");
        DomainValidations.required(email, "email");
        DomainValidations.required(phone, "phone");
        DomainValidations.required(address, "address");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(updatedAt, "updatedAt");
        DomainValidations.required(cityId, "cityId");
        this.documentTypeId = documentTypeId;
        this.documentNumber = documentNumber;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.secondLastName = secondLastName;
        this.birthDate = birthDate;
        this.biologicalSexId = biologicalSexId;
        this.genderIdentity = genderIdentity;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.active = active;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
        this.cityId = cityId;
    }

    /** Crea un registro nuevo: genera el id y las fechas, y deja el evento de registro. */
    public static Patient register(
            UUID documentTypeId,
            String documentNumber,
            String firstName,
            String middleName,
            String lastName,
            String secondLastName,
            LocalDate birthDate,
            UUID biologicalSexId,
            UUID genderIdentity,
            String email,
            String phone,
            String address,
            UUID createdBy,
            UUID updatedBy,
            UUID cityId) {
        PatientId id = PatientId.generate();
        LocalDateTime now = LocalDateTime.now();
        Patient aggregate = new Patient(id, documentTypeId, documentNumber, firstName, middleName, lastName, secondLastName, birthDate, biologicalSexId, genderIdentity, email, phone, address, true, now, createdBy, now, updatedBy, cityId);
        aggregate.recordEvent(new PatientRegisteredEvent(id, now));
        return aggregate;
    }

    /** Reconstruye un registro que ya existía (viene de la base de datos). No genera eventos. */
    public static Patient restore(
            PatientId id,
            UUID documentTypeId,
            String documentNumber,
            String firstName,
            String middleName,
            String lastName,
            String secondLastName,
            LocalDate birthDate,
            UUID biologicalSexId,
            UUID genderIdentity,
            String email,
            String phone,
            String address,
            boolean active,
            LocalDateTime createdAt,
            UUID createdBy,
            LocalDateTime updatedAt,
            UUID updatedBy,
            UUID cityId) {
        return new Patient(id, documentTypeId, documentNumber, firstName, middleName, lastName, secondLastName, birthDate, biologicalSexId, genderIdentity, email, phone, address, active, createdAt, createdBy, updatedAt, updatedBy, cityId);
    }

    /** Cambia los datos del registro, actualiza la fecha de modificación y deja el evento. */
    public void update(
            UUID documentTypeId,
            String documentNumber,
            String firstName,
            String middleName,
            String lastName,
            String secondLastName,
            LocalDate birthDate,
            UUID biologicalSexId,
            UUID genderIdentity,
            String email,
            String phone,
            String address,
            UUID updatedBy,
            UUID cityId) {
        DomainValidations.required(documentTypeId, "documentTypeId");
        DomainValidations.required(documentNumber, "documentNumber");
        DomainValidations.required(firstName, "firstName");
        DomainValidations.required(lastName, "lastName");
        DomainValidations.required(birthDate, "birthDate");
        DomainValidations.required(biologicalSexId, "biologicalSexId");
        DomainValidations.required(genderIdentity, "genderIdentity");
        DomainValidations.required(email, "email");
        DomainValidations.required(phone, "phone");
        DomainValidations.required(address, "address");
        DomainValidations.required(cityId, "cityId");
        this.documentTypeId = documentTypeId;
        this.documentNumber = documentNumber;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.secondLastName = secondLastName;
        this.birthDate = birthDate;
        this.biologicalSexId = biologicalSexId;
        this.genderIdentity = genderIdentity;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.updatedBy = updatedBy;
        this.cityId = cityId;
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new PatientUpdatedEvent(this.id, now));
    }

    public PatientId id() { return id; }
    public UUID documentTypeId() { return documentTypeId; }
    public String documentNumber() { return documentNumber; }
    public String firstName() { return firstName; }
    public String middleName() { return middleName; }
    public String lastName() { return lastName; }
    public String secondLastName() { return secondLastName; }
    public LocalDate birthDate() { return birthDate; }
    public UUID biologicalSexId() { return biologicalSexId; }
    public UUID genderIdentity() { return genderIdentity; }
    public String email() { return email; }
    public String phone() { return phone; }
    public String address() { return address; }
    public boolean active() { return active; }
    public LocalDateTime createdAt() { return createdAt; }
    public UUID createdBy() { return createdBy; }
    public LocalDateTime updatedAt() { return updatedAt; }
    public UUID updatedBy() { return updatedBy; }
    public UUID cityId() { return cityId; }
}
