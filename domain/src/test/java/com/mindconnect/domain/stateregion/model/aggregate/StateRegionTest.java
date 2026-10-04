package com.mindconnect.domain.stateregion.model.aggregate;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.stateregion.event.StateRegionRegisteredEvent;
import com.mindconnect.domain.stateregion.event.StateRegionUpdatedEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StateRegionTest {

    @Test
    void registrarDejaElEventoDeRegistro() {
        StateRegion aggregate = StateRegion.register("valor-a", "valor-a", "valor-a", UUID.randomUUID());

        assertEquals(1, aggregate.domainEvents().size());
        StateRegionRegisteredEvent event = assertInstanceOf(
                StateRegionRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void registrarGeneraElIdYLosValoresPorDefecto() {
        StateRegion aggregate = StateRegion.register("valor-a", "valor-a", "valor-a", UUID.randomUUID());

        assertNotNull(aggregate.id());
        assertTrue(aggregate.isActive());
        assertNotNull(aggregate.createdAt());
    }

    @Test
    void actualizarCambiaLosDatosYDejaElEventoDeActualizacion() {
        StateRegion aggregate = StateRegion.register("valor-a", "valor-a", "valor-a", UUID.randomUUID());
        aggregate.clearDomainEvents();

        aggregate.update("valor-b", "valor-b", "valor-b", UUID.randomUUID());

        assertEquals("valor-b", aggregate.nameRegion());
        assertEquals(1, aggregate.domainEvents().size());
        assertInstanceOf(StateRegionUpdatedEvent.class, aggregate.domainEvents().getFirst());
    }

    @Test
    void registrarConUnCampoObligatorioNuloLanzaDomainException() {
        assertThrows(DomainException.class, () -> StateRegion.register(null, "valor-a", "valor-a", UUID.randomUUID()));
    }
}
