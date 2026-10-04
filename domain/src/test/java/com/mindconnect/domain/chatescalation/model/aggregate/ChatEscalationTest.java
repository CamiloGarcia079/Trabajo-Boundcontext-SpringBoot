package com.mindconnect.domain.chatescalation.model.aggregate;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.chatescalation.event.ChatEscalationRegisteredEvent;
import com.mindconnect.domain.chatescalation.event.ChatEscalationUpdatedEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ChatEscalationTest {

    @Test
    void registrarDejaElEventoDeRegistro() {
        ChatEscalation aggregate = ChatEscalation.register(UUID.randomUUID(), UUID.randomUUID(), true, "valor-a");

        assertEquals(1, aggregate.domainEvents().size());
        ChatEscalationRegisteredEvent event = assertInstanceOf(
                ChatEscalationRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void registrarGeneraElIdYLosValoresPorDefecto() {
        ChatEscalation aggregate = ChatEscalation.register(UUID.randomUUID(), UUID.randomUUID(), true, "valor-a");

        assertNotNull(aggregate.id());
        assertNotNull(aggregate.createdAt());
    }

    @Test
    void actualizarCambiaLosDatosYDejaElEventoDeActualizacion() {
        ChatEscalation aggregate = ChatEscalation.register(UUID.randomUUID(), UUID.randomUUID(), true, "valor-a");
        aggregate.clearDomainEvents();

        aggregate.update(UUID.randomUUID(), UUID.randomUUID(), false, "valor-b");

        assertEquals(false, aggregate.fromAi());
        assertEquals(1, aggregate.domainEvents().size());
        assertInstanceOf(ChatEscalationUpdatedEvent.class, aggregate.domainEvents().getFirst());
    }

    @Test
    void registrarConUnCampoObligatorioNuloLanzaDomainException() {
        assertThrows(DomainException.class, () -> ChatEscalation.register(null, UUID.randomUUID(), true, "valor-a"));
    }
}
