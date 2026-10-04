package com.mindconnect.domain.encounter.model.aggregate;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.encounter.event.EncounterRegisteredEvent;
import com.mindconnect.domain.encounter.event.EncounterUpdatedEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EncounterTest {

    @Test
    void registrarDejaElEventoDeRegistro() {
        Encounter aggregate = Encounter.register(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.now(), LocalDateTime.now(), "valor-a", "valor-a", UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());

        assertEquals(1, aggregate.domainEvents().size());
        EncounterRegisteredEvent event = assertInstanceOf(
                EncounterRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void registrarGeneraElIdYLosValoresPorDefecto() {
        Encounter aggregate = Encounter.register(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.now(), LocalDateTime.now(), "valor-a", "valor-a", UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());

        assertNotNull(aggregate.id());
        assertNotNull(aggregate.createdAt());
    }

    @Test
    void actualizarCambiaLosDatosYDejaElEventoDeActualizacion() {
        Encounter aggregate = Encounter.register(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.now(), LocalDateTime.now(), "valor-a", "valor-a", UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
        aggregate.clearDomainEvents();

        aggregate.update(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.now(), LocalDateTime.now(), "valor-b", "valor-b", UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());

        assertEquals("valor-b", aggregate.reasonForVisit());
        assertEquals(1, aggregate.domainEvents().size());
        assertInstanceOf(EncounterUpdatedEvent.class, aggregate.domainEvents().getFirst());
    }

    @Test
    void registrarConUnCampoObligatorioNuloLanzaDomainException() {
        assertThrows(DomainException.class, () -> Encounter.register(null, UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.now(), LocalDateTime.now(), "valor-a", "valor-a", UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID()));
    }
}
