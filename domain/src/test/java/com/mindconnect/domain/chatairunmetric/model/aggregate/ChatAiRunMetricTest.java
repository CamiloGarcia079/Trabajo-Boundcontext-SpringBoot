package com.mindconnect.domain.chatairunmetric.model.aggregate;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.chatairunmetric.event.ChatAiRunMetricRegisteredEvent;
import com.mindconnect.domain.chatairunmetric.event.ChatAiRunMetricUpdatedEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ChatAiRunMetricTest {

    @Test
    void registrarDejaElEventoDeRegistro() {
        ChatAiRunMetric aggregate = ChatAiRunMetric.register(UUID.randomUUID(), 1, 1, 1, BigDecimal.ONE);

        assertEquals(1, aggregate.domainEvents().size());
        ChatAiRunMetricRegisteredEvent event = assertInstanceOf(
                ChatAiRunMetricRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void registrarGeneraElIdYLosValoresPorDefecto() {
        ChatAiRunMetric aggregate = ChatAiRunMetric.register(UUID.randomUUID(), 1, 1, 1, BigDecimal.ONE);

        assertNotNull(aggregate.id());
        assertNotNull(aggregate.createdAt());
    }

    @Test
    void actualizarCambiaLosDatosYDejaElEventoDeActualizacion() {
        ChatAiRunMetric aggregate = ChatAiRunMetric.register(UUID.randomUUID(), 1, 1, 1, BigDecimal.ONE);
        aggregate.clearDomainEvents();

        aggregate.update(UUID.randomUUID(), 2, 2, 2, BigDecimal.TEN);

        assertEquals(2, aggregate.promptTokens());
        assertEquals(1, aggregate.domainEvents().size());
        assertInstanceOf(ChatAiRunMetricUpdatedEvent.class, aggregate.domainEvents().getFirst());
    }

    @Test
    void registrarConUnCampoObligatorioNuloLanzaDomainException() {
        assertThrows(DomainException.class, () -> ChatAiRunMetric.register(null, 1, 1, 1, BigDecimal.ONE));
    }
}
