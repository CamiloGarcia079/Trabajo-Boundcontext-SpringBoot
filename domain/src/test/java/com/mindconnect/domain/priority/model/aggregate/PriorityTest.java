package com.mindconnect.domain.priority.model.aggregate;


import org.junit.jupiter.api.Test;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.priority.event.PriorityRegisteredEvent;
import com.mindconnect.domain.priority.event.PriorityUpdatedEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PriorityTest {

    @Test
    void registrarDejaElEventoDeRegistro() {
        Priority aggregate = Priority.register("valor-a");

        assertEquals(1, aggregate.domainEvents().size());
        PriorityRegisteredEvent event = assertInstanceOf(
                PriorityRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void registrarGeneraElIdYLosValoresPorDefecto() {
        Priority aggregate = Priority.register("valor-a");

        assertNotNull(aggregate.id());
        assertNotNull(aggregate.createdAt());
    }

    @Test
    void actualizarCambiaLosDatosYDejaElEventoDeActualizacion() {
        Priority aggregate = Priority.register("valor-a");
        aggregate.clearDomainEvents();

        aggregate.update("valor-b");

        assertEquals("valor-b", aggregate.namePriority());
        assertEquals(1, aggregate.domainEvents().size());
        assertInstanceOf(PriorityUpdatedEvent.class, aggregate.domainEvents().getFirst());
    }

    @Test
    void registrarConUnCampoObligatorioNuloLanzaDomainException() {
        assertThrows(DomainException.class, () -> Priority.register(null));
    }
}
