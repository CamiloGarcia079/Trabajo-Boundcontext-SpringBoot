package com.mindconnect.domain.chatmessage.model.aggregate;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.chatmessage.event.ChatMessageRegisteredEvent;
import com.mindconnect.domain.chatmessage.event.ChatMessageUpdatedEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ChatMessageTest {

    @Test
    void registrarDejaElEventoDeRegistro() {
        ChatMessage aggregate = ChatMessage.register(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), "valor-a", "valor-a");

        assertEquals(1, aggregate.domainEvents().size());
        ChatMessageRegisteredEvent event = assertInstanceOf(
                ChatMessageRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void registrarGeneraElIdYLosValoresPorDefecto() {
        ChatMessage aggregate = ChatMessage.register(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), "valor-a", "valor-a");

        assertNotNull(aggregate.id());
        assertNotNull(aggregate.createdAt());
    }

    @Test
    void actualizarCambiaLosDatosYDejaElEventoDeActualizacion() {
        ChatMessage aggregate = ChatMessage.register(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), "valor-a", "valor-a");
        aggregate.clearDomainEvents();

        aggregate.update(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), "valor-b", "valor-b");

        assertEquals("valor-b", aggregate.content());
        assertEquals(1, aggregate.domainEvents().size());
        assertInstanceOf(ChatMessageUpdatedEvent.class, aggregate.domainEvents().getFirst());
    }

    @Test
    void registrarConUnCampoObligatorioNuloLanzaDomainException() {
        assertThrows(DomainException.class, () -> ChatMessage.register(null, UUID.randomUUID(), UUID.randomUUID(), "valor-a", "valor-a"));
    }
}
