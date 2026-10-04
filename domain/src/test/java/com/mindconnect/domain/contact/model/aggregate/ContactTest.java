package com.mindconnect.domain.contact.model.aggregate;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.contact.event.ContactRegisteredEvent;
import com.mindconnect.domain.contact.event.ContactUpdatedEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ContactTest {

    @Test
    void registrarDejaElEventoDeRegistro() {
        Contact aggregate = Contact.register("valor-a", "valor-a", "valor-a", UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());

        assertEquals(1, aggregate.domainEvents().size());
        ContactRegisteredEvent event = assertInstanceOf(
                ContactRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void registrarGeneraElIdYLosValoresPorDefecto() {
        Contact aggregate = Contact.register("valor-a", "valor-a", "valor-a", UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());

        assertNotNull(aggregate.id());
        assertNotNull(aggregate.createdAt());
    }

    @Test
    void actualizarCambiaLosDatosYDejaElEventoDeActualizacion() {
        Contact aggregate = Contact.register("valor-a", "valor-a", "valor-a", UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
        aggregate.clearDomainEvents();

        aggregate.update("valor-b", "valor-b", "valor-b", UUID.randomUUID(), UUID.randomUUID());

        assertEquals("valor-b", aggregate.fullName());
        assertEquals(1, aggregate.domainEvents().size());
        assertInstanceOf(ContactUpdatedEvent.class, aggregate.domainEvents().getFirst());
    }

    @Test
    void registrarConUnCampoObligatorioNuloLanzaDomainException() {
        assertThrows(DomainException.class, () -> Contact.register(null, "valor-a", "valor-a", UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID()));
    }
}
