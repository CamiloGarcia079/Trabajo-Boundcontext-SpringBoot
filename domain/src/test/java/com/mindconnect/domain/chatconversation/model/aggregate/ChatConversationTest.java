package com.mindconnect.domain.chatconversation.model.aggregate;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.chatconversation.event.ChatConversationRegisteredEvent;
import com.mindconnect.domain.chatconversation.event.ChatConversationUpdatedEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ChatConversationTest {

    @Test
    void registrarDejaElEventoDeRegistro() {
        ChatConversation aggregate = ChatConversation.register(UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.now(), true, LocalDateTime.now(), UUID.randomUUID());

        assertEquals(1, aggregate.domainEvents().size());
        ChatConversationRegisteredEvent event = assertInstanceOf(
                ChatConversationRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void registrarGeneraElIdYLosValoresPorDefecto() {
        ChatConversation aggregate = ChatConversation.register(UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.now(), true, LocalDateTime.now(), UUID.randomUUID());

        assertNotNull(aggregate.id());
        assertNotNull(aggregate.createdAt());
    }

    @Test
    void actualizarCambiaLosDatosYDejaElEventoDeActualizacion() {
        ChatConversation aggregate = ChatConversation.register(UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.now(), true, LocalDateTime.now(), UUID.randomUUID());
        aggregate.clearDomainEvents();

        aggregate.update(UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.now(), false, LocalDateTime.now(), UUID.randomUUID());

        assertEquals(false, aggregate.closed());
        assertEquals(1, aggregate.domainEvents().size());
        assertInstanceOf(ChatConversationUpdatedEvent.class, aggregate.domainEvents().getFirst());
    }

    @Test
    void registrarConUnCampoObligatorioNuloLanzaDomainException() {
        assertThrows(DomainException.class, () -> ChatConversation.register(null, UUID.randomUUID(), LocalDateTime.now(), true, LocalDateTime.now(), UUID.randomUUID()));
    }
}
