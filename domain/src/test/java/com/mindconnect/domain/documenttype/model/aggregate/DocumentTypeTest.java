package com.mindconnect.domain.documenttype.model.aggregate;


import org.junit.jupiter.api.Test;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.documenttype.event.DocumentTypeRegisteredEvent;
import com.mindconnect.domain.documenttype.event.DocumentTypeUpdatedEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DocumentTypeTest {

    @Test
    void registrarDejaElEventoDeRegistro() {
        DocumentType aggregate = DocumentType.register("valor-a", "valor-a");

        assertEquals(1, aggregate.domainEvents().size());
        DocumentTypeRegisteredEvent event = assertInstanceOf(
                DocumentTypeRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void registrarGeneraElIdYLosValoresPorDefecto() {
        DocumentType aggregate = DocumentType.register("valor-a", "valor-a");

        assertNotNull(aggregate.id());
        assertTrue(aggregate.active());
        assertNotNull(aggregate.createdAt());
    }

    @Test
    void actualizarCambiaLosDatosYDejaElEventoDeActualizacion() {
        DocumentType aggregate = DocumentType.register("valor-a", "valor-a");
        aggregate.clearDomainEvents();

        aggregate.update("valor-b", "valor-b");

        assertEquals("valor-b", aggregate.code());
        assertEquals(1, aggregate.domainEvents().size());
        assertInstanceOf(DocumentTypeUpdatedEvent.class, aggregate.domainEvents().getFirst());
    }

    @Test
    void registrarConUnCampoObligatorioNuloLanzaDomainException() {
        assertThrows(DomainException.class, () -> DocumentType.register(null, "valor-a"));
    }
}
