package com.mindconnect.domain.consenttype.model.aggregate;


import org.junit.jupiter.api.Test;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.consenttype.event.ConsentTypeRegisteredEvent;
import com.mindconnect.domain.consenttype.event.ConsentTypeUpdatedEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConsentTypeTest {

    @Test
    void registrarDejaElEventoDeRegistro() {
        ConsentType aggregate = ConsentType.register("valor-a", "valor-a", "valor-a");

        assertEquals(1, aggregate.domainEvents().size());
        ConsentTypeRegisteredEvent event = assertInstanceOf(
                ConsentTypeRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void registrarGeneraElIdYLosValoresPorDefecto() {
        ConsentType aggregate = ConsentType.register("valor-a", "valor-a", "valor-a");

        assertNotNull(aggregate.id());
        assertTrue(aggregate.active());
        assertNotNull(aggregate.createdAt());
    }

    @Test
    void actualizarCambiaLosDatosYDejaElEventoDeActualizacion() {
        ConsentType aggregate = ConsentType.register("valor-a", "valor-a", "valor-a");
        aggregate.clearDomainEvents();

        aggregate.update("valor-b", "valor-b", "valor-b");

        assertEquals("valor-b", aggregate.code());
        assertEquals(1, aggregate.domainEvents().size());
        assertInstanceOf(ConsentTypeUpdatedEvent.class, aggregate.domainEvents().getFirst());
    }

    @Test
    void registrarConUnCampoObligatorioNuloLanzaDomainException() {
        assertThrows(DomainException.class, () -> ConsentType.register(null, "valor-a", "valor-a"));
    }
}
