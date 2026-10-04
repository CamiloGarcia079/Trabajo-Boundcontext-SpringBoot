package com.mindconnect.domain.chatairunerror.model.aggregate;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.chatairunerror.event.ChatAiRunErrorRegisteredEvent;
import com.mindconnect.domain.chatairunerror.event.ChatAiRunErrorUpdatedEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ChatAiRunErrorTest {

    @Test
    void registrarDejaElEventoDeRegistro() {
        ChatAiRunError aggregate = ChatAiRunError.register(UUID.randomUUID(), "valor-a", "valor-a", "valor-a");

        assertEquals(1, aggregate.domainEvents().size());
        ChatAiRunErrorRegisteredEvent event = assertInstanceOf(
                ChatAiRunErrorRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void registrarGeneraElIdYLosValoresPorDefecto() {
        ChatAiRunError aggregate = ChatAiRunError.register(UUID.randomUUID(), "valor-a", "valor-a", "valor-a");

        assertNotNull(aggregate.id());
        assertNotNull(aggregate.createdAt());
    }

    @Test
    void actualizarCambiaLosDatosYDejaElEventoDeActualizacion() {
        ChatAiRunError aggregate = ChatAiRunError.register(UUID.randomUUID(), "valor-a", "valor-a", "valor-a");
        aggregate.clearDomainEvents();

        aggregate.update(UUID.randomUUID(), "valor-b", "valor-b", "valor-b");

        assertEquals("valor-b", aggregate.errorMessage());
        assertEquals(1, aggregate.domainEvents().size());
        assertInstanceOf(ChatAiRunErrorUpdatedEvent.class, aggregate.domainEvents().getFirst());
    }

    @Test
    void registrarConUnCampoObligatorioNuloLanzaDomainException() {
        assertThrows(DomainException.class, () -> ChatAiRunError.register(null, "valor-a", "valor-a", "valor-a"));
    }
}
