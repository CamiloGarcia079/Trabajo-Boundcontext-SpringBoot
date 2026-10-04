package com.mindconnect.domain.sendertype.model.aggregate;


import org.junit.jupiter.api.Test;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.sendertype.event.SenderTypeRegisteredEvent;
import com.mindconnect.domain.sendertype.event.SenderTypeUpdatedEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SenderTypeTest {

    @Test
    void registrarDejaElEventoDeRegistro() {
        SenderType aggregate = SenderType.register("valor-a");

        assertEquals(1, aggregate.domainEvents().size());
        SenderTypeRegisteredEvent event = assertInstanceOf(
                SenderTypeRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void registrarGeneraElIdYLosValoresPorDefecto() {
        SenderType aggregate = SenderType.register("valor-a");

        assertNotNull(aggregate.id());
        assertNotNull(aggregate.createdAt());
    }

    @Test
    void actualizarCambiaLosDatosYDejaElEventoDeActualizacion() {
        SenderType aggregate = SenderType.register("valor-a");
        aggregate.clearDomainEvents();

        aggregate.update("valor-b");

        assertEquals("valor-b", aggregate.nameType());
        assertEquals(1, aggregate.domainEvents().size());
        assertInstanceOf(SenderTypeUpdatedEvent.class, aggregate.domainEvents().getFirst());
    }

    @Test
    void registrarConUnCampoObligatorioNuloLanzaDomainException() {
        assertThrows(DomainException.class, () -> SenderType.register(null));
    }
}
