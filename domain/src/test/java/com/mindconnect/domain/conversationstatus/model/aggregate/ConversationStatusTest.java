package com.mindconnect.domain.conversationstatus.model.aggregate;


import org.junit.jupiter.api.Test;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.conversationstatus.event.ConversationStatusRegisteredEvent;
import com.mindconnect.domain.conversationstatus.event.ConversationStatusUpdatedEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConversationStatusTest {

    @Test
    void registrarDejaElEventoDeRegistro() {
        ConversationStatus aggregate = ConversationStatus.register("valor-a");

        assertEquals(1, aggregate.domainEvents().size());
        ConversationStatusRegisteredEvent event = assertInstanceOf(
                ConversationStatusRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void registrarGeneraElIdYLosValoresPorDefecto() {
        ConversationStatus aggregate = ConversationStatus.register("valor-a");

        assertNotNull(aggregate.id());
        assertNotNull(aggregate.createdAt());
    }

    @Test
    void actualizarCambiaLosDatosYDejaElEventoDeActualizacion() {
        ConversationStatus aggregate = ConversationStatus.register("valor-a");
        aggregate.clearDomainEvents();

        aggregate.update("valor-b");

        assertEquals("valor-b", aggregate.nameStatus());
        assertEquals(1, aggregate.domainEvents().size());
        assertInstanceOf(ConversationStatusUpdatedEvent.class, aggregate.domainEvents().getFirst());
    }

    @Test
    void registrarConUnCampoObligatorioNuloLanzaDomainException() {
        assertThrows(DomainException.class, () -> ConversationStatus.register(null));
    }
}
