package com.mindconnect.domain.patientcontact.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import com.mindconnect.domain.common.model.AggregateRoot;
import com.mindconnect.domain.common.validation.DomainValidations;
import com.mindconnect.domain.patientcontact.event.PatientContactRegisteredEvent;
import com.mindconnect.domain.patientcontact.event.PatientContactUpdatedEvent;
import com.mindconnect.domain.patientcontact.model.valueobject.PatientContactId;

/**
 * Agregado raíz del contexto patientcontact: representa la tabla patient_contacts.
 *
 * <p>Relaciones (se guardan solo por id, no como objetos):</p>
 * <ul>
 *   <li>contactId -> Contact</li>
 *   <li>patientId -> Patient</li>
 *   <li>relationshipTypeId -> RelationshipType</li>
 * </ul>
 *
 * <p>El id y las fechas los pone el propio agregado. No usa Spring ni JPA.</p>
 */
public class PatientContact extends AggregateRoot {

    private final PatientContactId id;
    private UUID contactId;
    private UUID patientId;
    private boolean isPrimaryContact;
    private boolean isEmergencyContact;
    private UUID relationshipTypeId;

    private PatientContact(
            PatientContactId id,
            UUID contactId,
            UUID patientId,
            boolean isPrimaryContact,
            boolean isEmergencyContact,
            UUID relationshipTypeId) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        DomainValidations.required(contactId, "contactId");
        DomainValidations.required(patientId, "patientId");
        DomainValidations.required(relationshipTypeId, "relationshipTypeId");
        this.contactId = contactId;
        this.patientId = patientId;
        this.isPrimaryContact = isPrimaryContact;
        this.isEmergencyContact = isEmergencyContact;
        this.relationshipTypeId = relationshipTypeId;
    }

    /** Crea un registro nuevo: genera el id y las fechas, y deja el evento de registro. */
    public static PatientContact register(
            UUID contactId,
            UUID patientId,
            boolean isPrimaryContact,
            boolean isEmergencyContact,
            UUID relationshipTypeId) {
        PatientContactId id = PatientContactId.generate();
        LocalDateTime now = LocalDateTime.now();
        PatientContact aggregate = new PatientContact(id, contactId, patientId, isPrimaryContact, isEmergencyContact, relationshipTypeId);
        aggregate.recordEvent(new PatientContactRegisteredEvent(id, now));
        return aggregate;
    }

    /** Reconstruye un registro que ya existía (viene de la base de datos). No genera eventos. */
    public static PatientContact restore(
            PatientContactId id,
            UUID contactId,
            UUID patientId,
            boolean isPrimaryContact,
            boolean isEmergencyContact,
            UUID relationshipTypeId) {
        return new PatientContact(id, contactId, patientId, isPrimaryContact, isEmergencyContact, relationshipTypeId);
    }

    /** Cambia los datos del registro, actualiza la fecha de modificación y deja el evento. */
    public void update(
            UUID contactId,
            UUID patientId,
            boolean isPrimaryContact,
            boolean isEmergencyContact,
            UUID relationshipTypeId) {
        DomainValidations.required(contactId, "contactId");
        DomainValidations.required(patientId, "patientId");
        DomainValidations.required(relationshipTypeId, "relationshipTypeId");
        this.contactId = contactId;
        this.patientId = patientId;
        this.isPrimaryContact = isPrimaryContact;
        this.isEmergencyContact = isEmergencyContact;
        this.relationshipTypeId = relationshipTypeId;
        LocalDateTime now = LocalDateTime.now();
        recordEvent(new PatientContactUpdatedEvent(this.id, now));
    }

    public PatientContactId id() { return id; }
    public UUID contactId() { return contactId; }
    public UUID patientId() { return patientId; }
    public boolean isPrimaryContact() { return isPrimaryContact; }
    public boolean isEmergencyContact() { return isEmergencyContact; }
    public UUID relationshipTypeId() { return relationshipTypeId; }
}
