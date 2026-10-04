package com.mindconnect.domain.messagetype.model.aggregate;


import org.junit.jupiter.api.Test;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.messagetype.event.MessageTypeRegisteredEvent;
import com.mindconnect.domain.messagetype.event.MessageTypeUpdatedEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MessageTypeTest {

    @Test
    void registrarDejaElEventoDeRegistro() {
        MessageType aggregate = MessageType.register("valor-a");

        assertEquals(1, aggregate.domainEvents().size());
        MessageTypeRegisteredEvent event = assertInstanceOf(
                MessageTypeRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void registrarGeneraElIdYLosValoresPorDefecto() {
        MessageType aggregate = MessageType.register("valor-a");

        assertNotNull(aggregate.id());
        assertNotNull(aggregate.createdAt());
    }

    @Test
    void actualizarCambiaLosDatosYDejaElEventoDeActualizacion() {
        MessageType aggregate = MessageType.register("valor-a");
        aggregate.clearDomainEvents();

        aggregate.update("valor-b");

        assertEquals("valor-b", aggregate.nameType());
        assertEquals(1, aggregate.domainEvents().size());
        assertInstanceOf(MessageTypeUpdatedEvent.class, aggregate.domainEvents().getFirst());
    }

    @Test
    void registrarConUnCampoObligatorioNuloLanzaDomainException() {
        assertThrows(DomainException.class, () -> MessageType.register(null));
    }
}
