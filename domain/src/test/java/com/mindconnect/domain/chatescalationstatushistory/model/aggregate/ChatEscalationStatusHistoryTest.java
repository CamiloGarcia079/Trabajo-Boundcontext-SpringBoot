package com.mindconnect.domain.chatescalationstatushistory.model.aggregate;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.chatescalationstatushistory.event.ChatEscalationStatusHistoryRegisteredEvent;
import com.mindconnect.domain.chatescalationstatushistory.event.ChatEscalationStatusHistoryUpdatedEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ChatEscalationStatusHistoryTest {

    @Test
    void registrarDejaElEventoDeRegistro() {
        ChatEscalationStatusHistory aggregate = ChatEscalationStatusHistory.register(UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.now());

        assertEquals(1, aggregate.domainEvents().size());
        ChatEscalationStatusHistoryRegisteredEvent event = assertInstanceOf(
                ChatEscalationStatusHistoryRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void registrarGeneraElIdYLosValoresPorDefecto() {
        ChatEscalationStatusHistory aggregate = ChatEscalationStatusHistory.register(UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.now());

        assertNotNull(aggregate.id());
        assertNotNull(aggregate.createdAt());
    }

    @Test
    void actualizarCambiaLosDatosYDejaElEventoDeActualizacion() {
        ChatEscalationStatusHistory aggregate = ChatEscalationStatusHistory.register(UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.now());
        aggregate.clearDomainEvents();

        aggregate.update(UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.now());

        assertEquals(1, aggregate.domainEvents().size());
        assertInstanceOf(ChatEscalationStatusHistoryUpdatedEvent.class, aggregate.domainEvents().getFirst());
    }

    @Test
    void registrarConUnCampoObligatorioNuloLanzaDomainException() {
        assertThrows(DomainException.class, () -> ChatEscalationStatusHistory.register(null, UUID.randomUUID(), LocalDateTime.now()));
    }
}
