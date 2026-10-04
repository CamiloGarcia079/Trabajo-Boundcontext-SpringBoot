package com.mindconnect.domain.risklevel.model.aggregate;


import org.junit.jupiter.api.Test;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.risklevel.event.RiskLevelRegisteredEvent;
import com.mindconnect.domain.risklevel.event.RiskLevelUpdatedEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RiskLevelTest {

    @Test
    void registrarDejaElEventoDeRegistro() {
        RiskLevel aggregate = RiskLevel.register("valor-a", "valor-a", 1);

        assertEquals(1, aggregate.domainEvents().size());
        RiskLevelRegisteredEvent event = assertInstanceOf(
                RiskLevelRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void registrarGeneraElIdYLosValoresPorDefecto() {
        RiskLevel aggregate = RiskLevel.register("valor-a", "valor-a", 1);

        assertNotNull(aggregate.id());
        assertTrue(aggregate.active());
        assertNotNull(aggregate.createdAt());
    }

    @Test
    void actualizarCambiaLosDatosYDejaElEventoDeActualizacion() {
        RiskLevel aggregate = RiskLevel.register("valor-a", "valor-a", 1);
        aggregate.clearDomainEvents();

        aggregate.update("valor-b", "valor-b", 2);

        assertEquals("valor-b", aggregate.code());
        assertEquals(1, aggregate.domainEvents().size());
        assertInstanceOf(RiskLevelUpdatedEvent.class, aggregate.domainEvents().getFirst());
    }

    @Test
    void registrarConUnCampoObligatorioNuloLanzaDomainException() {
        assertThrows(DomainException.class, () -> RiskLevel.register(null, "valor-a", 1));
    }
}
