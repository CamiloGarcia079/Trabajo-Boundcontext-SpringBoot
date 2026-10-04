package com.mindconnect.domain.phonecontact.model.aggregate;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.phonecontact.event.PhoneContactRegisteredEvent;
import com.mindconnect.domain.phonecontact.event.PhoneContactUpdatedEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PhoneContactTest {

    @Test
    void registrarDejaElEventoDeRegistro() {
        PhoneContact aggregate = PhoneContact.register(UUID.randomUUID(), "valor-a", "valor-a");

        assertEquals(1, aggregate.domainEvents().size());
        PhoneContactRegisteredEvent event = assertInstanceOf(
                PhoneContactRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void registrarGeneraElIdYLosValoresPorDefecto() {
        PhoneContact aggregate = PhoneContact.register(UUID.randomUUID(), "valor-a", "valor-a");

        assertNotNull(aggregate.id());
    }

    @Test
    void actualizarCambiaLosDatosYDejaElEventoDeActualizacion() {
        PhoneContact aggregate = PhoneContact.register(UUID.randomUUID(), "valor-a", "valor-a");
        aggregate.clearDomainEvents();

        aggregate.update(UUID.randomUUID(), "valor-b", "valor-b");

        assertEquals("valor-b", aggregate.phone());
        assertEquals(1, aggregate.domainEvents().size());
        assertInstanceOf(PhoneContactUpdatedEvent.class, aggregate.domainEvents().getFirst());
    }

    @Test
    void registrarConUnCampoObligatorioNuloLanzaDomainException() {
        assertThrows(DomainException.class, () -> PhoneContact.register(null, "valor-a", "valor-a"));
    }
}
