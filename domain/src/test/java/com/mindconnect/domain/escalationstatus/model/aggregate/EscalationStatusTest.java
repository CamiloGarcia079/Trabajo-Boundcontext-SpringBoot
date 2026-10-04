package com.mindconnect.domain.escalationstatus.model.aggregate;


import org.junit.jupiter.api.Test;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.escalationstatus.event.EscalationStatusRegisteredEvent;
import com.mindconnect.domain.escalationstatus.event.EscalationStatusUpdatedEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EscalationStatusTest {

    @Test
    void registrarDejaElEventoDeRegistro() {
        EscalationStatus aggregate = EscalationStatus.register("valor-a");

        assertEquals(1, aggregate.domainEvents().size());
        EscalationStatusRegisteredEvent event = assertInstanceOf(
                EscalationStatusRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void registrarGeneraElIdYLosValoresPorDefecto() {
        EscalationStatus aggregate = EscalationStatus.register("valor-a");

        assertNotNull(aggregate.id());
        assertNotNull(aggregate.createdAt());
    }

    @Test
    void actualizarCambiaLosDatosYDejaElEventoDeActualizacion() {
        EscalationStatus aggregate = EscalationStatus.register("valor-a");
        aggregate.clearDomainEvents();

        aggregate.update("valor-b");

        assertEquals("valor-b", aggregate.nameStatus());
        assertEquals(1, aggregate.domainEvents().size());
        assertInstanceOf(EscalationStatusUpdatedEvent.class, aggregate.domainEvents().getFirst());
    }

    @Test
    void registrarConUnCampoObligatorioNuloLanzaDomainException() {
        assertThrows(DomainException.class, () -> EscalationStatus.register(null));
    }
}
