package com.mindconnect.domain.airunstatus.model.aggregate;


import org.junit.jupiter.api.Test;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.airunstatus.event.AiRunStatusRegisteredEvent;
import com.mindconnect.domain.airunstatus.event.AiRunStatusUpdatedEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AiRunStatusTest {

    @Test
    void registrarDejaElEventoDeRegistro() {
        AiRunStatus aggregate = AiRunStatus.register("valor-a");

        assertEquals(1, aggregate.domainEvents().size());
        AiRunStatusRegisteredEvent event = assertInstanceOf(
                AiRunStatusRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void registrarGeneraElIdYLosValoresPorDefecto() {
        AiRunStatus aggregate = AiRunStatus.register("valor-a");

        assertNotNull(aggregate.id());
        assertNotNull(aggregate.createdAt());
    }

    @Test
    void actualizarCambiaLosDatosYDejaElEventoDeActualizacion() {
        AiRunStatus aggregate = AiRunStatus.register("valor-a");
        aggregate.clearDomainEvents();

        aggregate.update("valor-b");

        assertEquals("valor-b", aggregate.nameStatus());
        assertEquals(1, aggregate.domainEvents().size());
        assertInstanceOf(AiRunStatusUpdatedEvent.class, aggregate.domainEvents().getFirst());
    }

    @Test
    void registrarConUnCampoObligatorioNuloLanzaDomainException() {
        assertThrows(DomainException.class, () -> AiRunStatus.register(null));
    }
}
