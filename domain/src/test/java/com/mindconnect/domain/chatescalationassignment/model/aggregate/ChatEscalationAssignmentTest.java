package com.mindconnect.domain.chatescalationassignment.model.aggregate;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.chatescalationassignment.event.ChatEscalationAssignmentRegisteredEvent;
import com.mindconnect.domain.chatescalationassignment.event.ChatEscalationAssignmentUpdatedEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ChatEscalationAssignmentTest {

    @Test
    void registrarDejaElEventoDeRegistro() {
        ChatEscalationAssignment aggregate = ChatEscalationAssignment.register(UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.now());

        assertEquals(1, aggregate.domainEvents().size());
        ChatEscalationAssignmentRegisteredEvent event = assertInstanceOf(
                ChatEscalationAssignmentRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void registrarGeneraElIdYLosValoresPorDefecto() {
        ChatEscalationAssignment aggregate = ChatEscalationAssignment.register(UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.now());

        assertNotNull(aggregate.id());
    }

    @Test
    void actualizarCambiaLosDatosYDejaElEventoDeActualizacion() {
        ChatEscalationAssignment aggregate = ChatEscalationAssignment.register(UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.now());
        aggregate.clearDomainEvents();

        aggregate.update(UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.now());

        assertEquals(1, aggregate.domainEvents().size());
        assertInstanceOf(ChatEscalationAssignmentUpdatedEvent.class, aggregate.domainEvents().getFirst());
    }

    @Test
    void registrarConUnCampoObligatorioNuloLanzaDomainException() {
        assertThrows(DomainException.class, () -> ChatEscalationAssignment.register(null, UUID.randomUUID(), LocalDateTime.now()));
    }
}
