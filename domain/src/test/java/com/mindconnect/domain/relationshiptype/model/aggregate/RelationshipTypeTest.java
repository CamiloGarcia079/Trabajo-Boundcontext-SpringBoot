package com.mindconnect.domain.relationshiptype.model.aggregate;


import org.junit.jupiter.api.Test;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.relationshiptype.event.RelationshipTypeRegisteredEvent;
import com.mindconnect.domain.relationshiptype.event.RelationshipTypeUpdatedEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RelationshipTypeTest {

    @Test
    void registrarDejaElEventoDeRegistro() {
        RelationshipType aggregate = RelationshipType.register("valor-a");

        assertEquals(1, aggregate.domainEvents().size());
        RelationshipTypeRegisteredEvent event = assertInstanceOf(
                RelationshipTypeRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void registrarGeneraElIdYLosValoresPorDefecto() {
        RelationshipType aggregate = RelationshipType.register("valor-a");

        assertNotNull(aggregate.id());
    }

    @Test
    void actualizarCambiaLosDatosYDejaElEventoDeActualizacion() {
        RelationshipType aggregate = RelationshipType.register("valor-a");
        aggregate.clearDomainEvents();

        aggregate.update("valor-b");

        assertEquals("valor-b", aggregate.description());
        assertEquals(1, aggregate.domainEvents().size());
        assertInstanceOf(RelationshipTypeUpdatedEvent.class, aggregate.domainEvents().getFirst());
    }

    @Test
    void registrarConUnCampoObligatorioNuloLanzaDomainException() {
        assertThrows(DomainException.class, () -> RelationshipType.register(null));
    }
}
