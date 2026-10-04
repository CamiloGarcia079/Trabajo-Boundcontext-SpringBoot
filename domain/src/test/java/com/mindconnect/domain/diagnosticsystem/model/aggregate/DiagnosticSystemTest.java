package com.mindconnect.domain.diagnosticsystem.model.aggregate;


import org.junit.jupiter.api.Test;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.diagnosticsystem.event.DiagnosticSystemRegisteredEvent;
import com.mindconnect.domain.diagnosticsystem.event.DiagnosticSystemUpdatedEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DiagnosticSystemTest {

    @Test
    void registrarDejaElEventoDeRegistro() {
        DiagnosticSystem aggregate = DiagnosticSystem.register("valor-a", "valor-a", "valor-a");

        assertEquals(1, aggregate.domainEvents().size());
        DiagnosticSystemRegisteredEvent event = assertInstanceOf(
                DiagnosticSystemRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void registrarGeneraElIdYLosValoresPorDefecto() {
        DiagnosticSystem aggregate = DiagnosticSystem.register("valor-a", "valor-a", "valor-a");

        assertNotNull(aggregate.id());
        assertTrue(aggregate.active());
        assertNotNull(aggregate.createdAt());
    }

    @Test
    void actualizarCambiaLosDatosYDejaElEventoDeActualizacion() {
        DiagnosticSystem aggregate = DiagnosticSystem.register("valor-a", "valor-a", "valor-a");
        aggregate.clearDomainEvents();

        aggregate.update("valor-b", "valor-b", "valor-b");

        assertEquals("valor-b", aggregate.code());
        assertEquals(1, aggregate.domainEvents().size());
        assertInstanceOf(DiagnosticSystemUpdatedEvent.class, aggregate.domainEvents().getFirst());
    }

    @Test
    void registrarConUnCampoObligatorioNuloLanzaDomainException() {
        assertThrows(DomainException.class, () -> DiagnosticSystem.register(null, "valor-a", "valor-a"));
    }
}
