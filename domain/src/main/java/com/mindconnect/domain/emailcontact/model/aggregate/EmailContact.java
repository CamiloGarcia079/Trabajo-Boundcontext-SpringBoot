package com.mindconnect.domain.emailcontact.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import com.mindconnect.domain.common.model.AggregateRoot;
import com.mindconnect.domain.common.validation.DomainValidations;
import com.mindconnect.domain.emailcontact.event.EmailContactRegisteredEvent;
import com.mindconnect.domain.emailcontact.event.EmailContactUpdatedEvent;
import com.mindconnect.domain.emailcontact.model.valueobject.EmailContactId;

/**
 * Agregado raíz del contexto emailcontact: representa la tabla email_contacts.
 *
 * <p>Relaciones (se guardan solo por id, no como objetos):</p>
 * <ul>
 *   <li>contactId -> Contact</li>
 * </ul>
 *
 * <p>El id y las fechas los pone el propio agregado. No usa Spring ni JPA.</p>
 */
public class EmailContact extends AggregateRoot {

    private final EmailContactId id;
    private UUID contactId;
    private String email;
    private String notes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private EmailContact(
            EmailContactId id,
            UUID contactId,
            String email,
            String notes,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        DomainValidations.required(contactId, "contactId");
        DomainValidations.required(email, "email");
        DomainValidations.required(notes, "notes");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(updatedAt, "updatedAt");
        this.contactId = contactId;
        this.email = email;
        this.notes = notes;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /** Crea un registro nuevo: genera el id y las fechas, y deja el evento de registro. */
    public static EmailContact register(
            UUID contactId,
            String email,
            String notes) {
        EmailContactId id = EmailContactId.generate();
        LocalDateTime now = LocalDateTime.now();
        EmailContact aggregate = new EmailContact(id, contactId, email, notes, now, now);
        aggregate.recordEvent(new EmailContactRegisteredEvent(id, now));
        return aggregate;
    }

    /** Reconstruye un registro que ya existía (viene de la base de datos). No genera eventos. */
    public static EmailContact restore(
            EmailContactId id,
            UUID contactId,
            String email,
            String notes,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new EmailContact(id, contactId, email, notes, createdAt, updatedAt);
    }

    /** Cambia los datos del registro, actualiza la fecha de modificación y deja el evento. */
    public void update(
            UUID contactId,
            String email,
            String notes) {
        DomainValidations.required(contactId, "contactId");
        DomainValidations.required(email, "email");
        DomainValidations.required(notes, "notes");
        this.contactId = contactId;
        this.email = email;
        this.notes = notes;
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new EmailContactUpdatedEvent(this.id, now));
    }

    public EmailContactId id() { return id; }
    public UUID contactId() { return contactId; }
    public String email() { return email; }
    public String notes() { return notes; }
    public LocalDateTime createdAt() { return createdAt; }
    public LocalDateTime updatedAt() { return updatedAt; }
}
