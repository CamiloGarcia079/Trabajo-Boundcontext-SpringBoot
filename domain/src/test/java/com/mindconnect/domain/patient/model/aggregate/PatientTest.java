package com.mindconnect.domain.patient.model.aggregate;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.patient.event.PatientRegisteredEvent;
import com.mindconnect.domain.patient.event.PatientUpdatedEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PatientTest {

    @Test
    void registrarDejaElEventoDeRegistro() {
        Patient aggregate = Patient.register(UUID.randomUUID(), "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", LocalDate.now(), UUID.randomUUID(), UUID.randomUUID(), "valor-a", "valor-a", "valor-a", UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());

        assertEquals(1, aggregate.domainEvents().size());
        PatientRegisteredEvent event = assertInstanceOf(
                PatientRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void registrarGeneraElIdYLosValoresPorDefecto() {
        Patient aggregate = Patient.register(UUID.randomUUID(), "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", LocalDate.now(), UUID.randomUUID(), UUID.randomUUID(), "valor-a", "valor-a", "valor-a", UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());

        assertNotNull(aggregate.id());
        assertTrue(aggregate.active());
        assertNotNull(aggregate.createdAt());
    }

    @Test
    void actualizarCambiaLosDatosYDejaElEventoDeActualizacion() {
        Patient aggregate = Patient.register(UUID.randomUUID(), "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", LocalDate.now(), UUID.randomUUID(), UUID.randomUUID(), "valor-a", "valor-a", "valor-a", UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
        aggregate.clearDomainEvents();

        aggregate.update(UUID.randomUUID(), "valor-b", "valor-b", "valor-b", "valor-b", "valor-b", LocalDate.now(), UUID.randomUUID(), UUID.randomUUID(), "valor-b", "valor-b", "valor-b", UUID.randomUUID(), UUID.randomUUID());

        assertEquals("valor-b", aggregate.documentNumber());
        assertEquals(1, aggregate.domainEvents().size());
        assertInstanceOf(PatientUpdatedEvent.class, aggregate.domainEvents().getFirst());
    }

    @Test
    void registrarConUnCampoObligatorioNuloLanzaDomainException() {
        assertThrows(DomainException.class, () -> Patient.register(null, "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", LocalDate.now(), UUID.randomUUID(), UUID.randomUUID(), "valor-a", "valor-a", "valor-a", UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID()));
    }
}
