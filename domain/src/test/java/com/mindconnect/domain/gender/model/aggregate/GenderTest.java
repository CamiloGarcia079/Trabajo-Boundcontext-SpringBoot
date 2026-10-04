package com.mindconnect.domain.gender.model.aggregate;


import org.junit.jupiter.api.Test;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.gender.event.GenderRegisteredEvent;
import com.mindconnect.domain.gender.event.GenderUpdatedEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GenderTest {

    @Test
    void registrarDejaElEventoDeRegistro() {
        Gender aggregate = Gender.register("valor-a");

        assertEquals(1, aggregate.domainEvents().size());
        GenderRegisteredEvent event = assertInstanceOf(
                GenderRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void registrarGeneraElIdYLosValoresPorDefecto() {
        Gender aggregate = Gender.register("valor-a");

        assertNotNull(aggregate.id());
        assertNotNull(aggregate.createdAt());
    }

    @Test
    void actualizarCambiaLosDatosYDejaElEventoDeActualizacion() {
        Gender aggregate = Gender.register("valor-a");
        aggregate.clearDomainEvents();

        aggregate.update("valor-b");

        assertEquals("valor-b", aggregate.description());
        assertEquals(1, aggregate.domainEvents().size());
        assertInstanceOf(GenderUpdatedEvent.class, aggregate.domainEvents().getFirst());
    }

    @Test
    void registrarConUnCampoObligatorioNuloLanzaDomainException() {
        assertThrows(DomainException.class, () -> Gender.register(null));
    }
}
