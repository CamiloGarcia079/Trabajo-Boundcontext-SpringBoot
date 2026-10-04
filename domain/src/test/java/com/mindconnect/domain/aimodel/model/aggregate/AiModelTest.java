package com.mindconnect.domain.aimodel.model.aggregate;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.aimodel.event.AiModelRegisteredEvent;
import com.mindconnect.domain.aimodel.event.AiModelUpdatedEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AiModelTest {

    @Test
    void registrarDejaElEventoDeRegistro() {
        AiModel aggregate = AiModel.register(UUID.randomUUID(), "valor-a", "valor-a", BigDecimal.ONE, BigDecimal.ONE, 1, 1);

        assertEquals(1, aggregate.domainEvents().size());
        AiModelRegisteredEvent event = assertInstanceOf(
                AiModelRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void registrarGeneraElIdYLosValoresPorDefecto() {
        AiModel aggregate = AiModel.register(UUID.randomUUID(), "valor-a", "valor-a", BigDecimal.ONE, BigDecimal.ONE, 1, 1);

        assertNotNull(aggregate.id());
        assertTrue(aggregate.isActive());
        assertNotNull(aggregate.createdAt());
    }

    @Test
    void actualizarCambiaLosDatosYDejaElEventoDeActualizacion() {
        AiModel aggregate = AiModel.register(UUID.randomUUID(), "valor-a", "valor-a", BigDecimal.ONE, BigDecimal.ONE, 1, 1);
        aggregate.clearDomainEvents();

        aggregate.update(UUID.randomUUID(), "valor-b", "valor-b", BigDecimal.TEN, BigDecimal.TEN, 2, 2);

        assertEquals("valor-b", aggregate.nameModel());
        assertEquals(1, aggregate.domainEvents().size());
        assertInstanceOf(AiModelUpdatedEvent.class, aggregate.domainEvents().getFirst());
    }

    @Test
    void registrarConUnCampoObligatorioNuloLanzaDomainException() {
        assertThrows(DomainException.class, () -> AiModel.register(null, "valor-a", "valor-a", BigDecimal.ONE, BigDecimal.ONE, 1, 1));
    }
}
