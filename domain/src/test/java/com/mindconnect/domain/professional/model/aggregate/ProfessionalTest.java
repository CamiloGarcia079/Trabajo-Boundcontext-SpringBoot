package com.mindconnect.domain.professional.model.aggregate;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.professional.event.ProfessionalRegisteredEvent;
import com.mindconnect.domain.professional.event.ProfessionalUpdatedEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProfessionalTest {

    @Test
    void registrarDejaElEventoDeRegistro() {
        Professional aggregate = Professional.register(UUID.randomUUID(), "valor-a", "valor-a", "valor-a", UUID.randomUUID(), "valor-a", UUID.randomUUID());

        assertEquals(1, aggregate.domainEvents().size());
        ProfessionalRegisteredEvent event = assertInstanceOf(
                ProfessionalRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void registrarGeneraElIdYLosValoresPorDefecto() {
        Professional aggregate = Professional.register(UUID.randomUUID(), "valor-a", "valor-a", "valor-a", UUID.randomUUID(), "valor-a", UUID.randomUUID());

        assertNotNull(aggregate.id());
        assertTrue(aggregate.active());
        assertNotNull(aggregate.createdAt());
    }

    @Test
    void actualizarCambiaLosDatosYDejaElEventoDeActualizacion() {
        Professional aggregate = Professional.register(UUID.randomUUID(), "valor-a", "valor-a", "valor-a", UUID.randomUUID(), "valor-a", UUID.randomUUID());
        aggregate.clearDomainEvents();

        aggregate.update(UUID.randomUUID(), "valor-b", "valor-b", "valor-b", UUID.randomUUID(), "valor-b", UUID.randomUUID());

        assertEquals("valor-b", aggregate.documentNumber());
        assertEquals(1, aggregate.domainEvents().size());
        assertInstanceOf(ProfessionalUpdatedEvent.class, aggregate.domainEvents().getFirst());
    }

    @Test
    void registrarConUnCampoObligatorioNuloLanzaDomainException() {
        assertThrows(DomainException.class, () -> Professional.register(null, "valor-a", "valor-a", "valor-a", UUID.randomUUID(), "valor-a", UUID.randomUUID()));
    }
}
