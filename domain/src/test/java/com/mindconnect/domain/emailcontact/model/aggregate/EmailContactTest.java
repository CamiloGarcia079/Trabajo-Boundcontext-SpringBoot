package com.mindconnect.domain.emailcontact.model.aggregate;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.emailcontact.event.EmailContactRegisteredEvent;
import com.mindconnect.domain.emailcontact.event.EmailContactUpdatedEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EmailContactTest {

    @Test
    void registrarDejaElEventoDeRegistro() {
        EmailContact aggregate = EmailContact.register(UUID.randomUUID(), "valor-a", "valor-a");

        assertEquals(1, aggregate.domainEvents().size());
        EmailContactRegisteredEvent event = assertInstanceOf(
                EmailContactRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void registrarGeneraElIdYLosValoresPorDefecto() {
        EmailContact aggregate = EmailContact.register(UUID.randomUUID(), "valor-a", "valor-a");

        assertNotNull(aggregate.id());
        assertNotNull(aggregate.createdAt());
    }

    @Test
    void actualizarCambiaLosDatosYDejaElEventoDeActualizacion() {
        EmailContact aggregate = EmailContact.register(UUID.randomUUID(), "valor-a", "valor-a");
        aggregate.clearDomainEvents();

        aggregate.update(UUID.randomUUID(), "valor-b", "valor-b");

        assertEquals("valor-b", aggregate.email());
        assertEquals(1, aggregate.domainEvents().size());
        assertInstanceOf(EmailContactUpdatedEvent.class, aggregate.domainEvents().getFirst());
    }

    @Test
    void registrarConUnCampoObligatorioNuloLanzaDomainException() {
        assertThrows(DomainException.class, () -> EmailContact.register(null, "valor-a", "valor-a"));
    }
}
