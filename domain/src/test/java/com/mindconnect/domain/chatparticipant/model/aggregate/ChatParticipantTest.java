package com.mindconnect.domain.chatparticipant.model.aggregate;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.chatparticipant.event.ChatParticipantRegisteredEvent;
import com.mindconnect.domain.chatparticipant.event.ChatParticipantUpdatedEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ChatParticipantTest {

    @Test
    void registrarDejaElEventoDeRegistro() {
        ChatParticipant aggregate = ChatParticipant.register(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());

        assertEquals(1, aggregate.domainEvents().size());
        ChatParticipantRegisteredEvent event = assertInstanceOf(
                ChatParticipantRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void registrarGeneraElIdYLosValoresPorDefecto() {
        ChatParticipant aggregate = ChatParticipant.register(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());

        assertNotNull(aggregate.id());
        assertNotNull(aggregate.createdAt());
    }

    @Test
    void actualizarCambiaLosDatosYDejaElEventoDeActualizacion() {
        ChatParticipant aggregate = ChatParticipant.register(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
        aggregate.clearDomainEvents();

        aggregate.update(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());

        assertEquals(1, aggregate.domainEvents().size());
        assertInstanceOf(ChatParticipantUpdatedEvent.class, aggregate.domainEvents().getFirst());
    }

    @Test
    void registrarConUnCampoObligatorioNuloLanzaDomainException() {
        assertThrows(DomainException.class, () -> ChatParticipant.register(null, UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID()));
    }
}
