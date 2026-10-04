package com.mindconnect.domain.phonecontact.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import com.mindconnect.domain.common.model.AggregateRoot;
import com.mindconnect.domain.common.validation.DomainValidations;
import com.mindconnect.domain.phonecontact.event.PhoneContactRegisteredEvent;
import com.mindconnect.domain.phonecontact.event.PhoneContactUpdatedEvent;
import com.mindconnect.domain.phonecontact.model.valueobject.PhoneContactId;

/**
 * Agregado raíz del contexto phonecontact: representa la tabla phone_contacts.
 *
 * <p>Relaciones (se guardan solo por id, no como objetos):</p>
 * <ul>
 *   <li>contactId -> Contact</li>
 * </ul>
 *
 * <p>El id y las fechas los pone el propio agregado. No usa Spring ni JPA.</p>
 */
public class PhoneContact extends AggregateRoot {

    private final PhoneContactId id;
    private UUID contactId;
    private String phone;
    private String notes;

    private PhoneContact(
            PhoneContactId id,
            UUID contactId,
            String phone,
            String notes) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        DomainValidations.required(contactId, "contactId");
        DomainValidations.required(notes, "notes");
        this.contactId = contactId;
        this.phone = phone;
        this.notes = notes;
    }

    /** Crea un registro nuevo: genera el id y las fechas, y deja el evento de registro. */
    public static PhoneContact register(
            UUID contactId,
            String phone,
            String notes) {
        PhoneContactId id = PhoneContactId.generate();
        LocalDateTime now = LocalDateTime.now();
        PhoneContact aggregate = new PhoneContact(id, contactId, phone, notes);
        aggregate.recordEvent(new PhoneContactRegisteredEvent(id, now));
        return aggregate;
    }

    /** Reconstruye un registro que ya existía (viene de la base de datos). No genera eventos. */
    public static PhoneContact restore(
            PhoneContactId id,
            UUID contactId,
            String phone,
            String notes) {
        return new PhoneContact(id, contactId, phone, notes);
    }

    /** Cambia los datos del registro, actualiza la fecha de modificación y deja el evento. */
    public void update(
            UUID contactId,
            String phone,
            String notes) {
        DomainValidations.required(contactId, "contactId");
        DomainValidations.required(notes, "notes");
        this.contactId = contactId;
        this.phone = phone;
        this.notes = notes;
        LocalDateTime now = LocalDateTime.now();
        recordEvent(new PhoneContactUpdatedEvent(this.id, now));
    }

    public PhoneContactId id() { return id; }
    public UUID contactId() { return contactId; }
    public String phone() { return phone; }
    public String notes() { return notes; }
}
