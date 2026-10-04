package com.mindconnect.domain.encountermodality.model.aggregate;


import org.junit.jupiter.api.Test;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.encountermodality.event.EncounterModalityRegisteredEvent;
import com.mindconnect.domain.encountermodality.event.EncounterModalityUpdatedEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EncounterModalityTest {

    @Test
    void registrarDejaElEventoDeRegistro() {
        EncounterModality aggregate = EncounterModality.register("valor-a", "valor-a");

        assertEquals(1, aggregate.domainEvents().size());
        EncounterModalityRegisteredEvent event = assertInstanceOf(
                EncounterModalityRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void registrarGeneraElIdYLosValoresPorDefecto() {
        EncounterModality aggregate = EncounterModality.register("valor-a", "valor-a");

        assertNotNull(aggregate.id());
        assertTrue(aggregate.active());
        assertNotNull(aggregate.createdAt());
    }

    @Test
    void actualizarCambiaLosDatosYDejaElEventoDeActualizacion() {
        EncounterModality aggregate = EncounterModality.register("valor-a", "valor-a");
        aggregate.clearDomainEvents();

        aggregate.update("valor-b", "valor-b");

        assertEquals("valor-b", aggregate.code());
        assertEquals(1, aggregate.domainEvents().size());
        assertInstanceOf(EncounterModalityUpdatedEvent.class, aggregate.domainEvents().getFirst());
    }

    @Test
    void registrarConUnCampoObligatorioNuloLanzaDomainException() {
        assertThrows(DomainException.class, () -> EncounterModality.register(null, "valor-a"));
    }
}
