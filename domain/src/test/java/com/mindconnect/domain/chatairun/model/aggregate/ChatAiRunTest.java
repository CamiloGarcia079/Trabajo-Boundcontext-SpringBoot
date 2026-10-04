package com.mindconnect.domain.chatairun.model.aggregate;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.chatairun.event.ChatAiRunRegisteredEvent;
import com.mindconnect.domain.chatairun.event.ChatAiRunUpdatedEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ChatAiRunTest {

    @Test
    void registrarDejaElEventoDeRegistro() {
        ChatAiRun aggregate = ChatAiRun.register(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());

        assertEquals(1, aggregate.domainEvents().size());
        ChatAiRunRegisteredEvent event = assertInstanceOf(
                ChatAiRunRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void registrarGeneraElIdYLosValoresPorDefecto() {
        ChatAiRun aggregate = ChatAiRun.register(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());

        assertNotNull(aggregate.id());
        assertNotNull(aggregate.createdAt());
    }

    @Test
    void actualizarCambiaLosDatosYDejaElEventoDeActualizacion() {
        ChatAiRun aggregate = ChatAiRun.register(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
        aggregate.clearDomainEvents();

        aggregate.update(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());

        assertEquals(1, aggregate.domainEvents().size());
        assertInstanceOf(ChatAiRunUpdatedEvent.class, aggregate.domainEvents().getFirst());
    }

    @Test
    void registrarConUnCampoObligatorioNuloLanzaDomainException() {
        assertThrows(DomainException.class, () -> ChatAiRun.register(null, UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID()));
    }
}
