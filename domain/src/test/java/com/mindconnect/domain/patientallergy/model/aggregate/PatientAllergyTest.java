package com.mindconnect.domain.patientallergy.model.aggregate;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.patientallergy.event.PatientAllergyRegisteredEvent;
import com.mindconnect.domain.patientallergy.event.PatientAllergyUpdatedEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PatientAllergyTest {

    @Test
    void registrarDejaElEventoDeRegistro() {
        PatientAllergy aggregate = PatientAllergy.register(UUID.randomUUID(), "valor-a", "valor-a", "valor-a", LocalDateTime.now(), UUID.randomUUID());

        assertEquals(1, aggregate.domainEvents().size());
        PatientAllergyRegisteredEvent event = assertInstanceOf(
                PatientAllergyRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void registrarGeneraElIdYLosValoresPorDefecto() {
        PatientAllergy aggregate = PatientAllergy.register(UUID.randomUUID(), "valor-a", "valor-a", "valor-a", LocalDateTime.now(), UUID.randomUUID());

        assertNotNull(aggregate.id());
        assertTrue(aggregate.active());
        assertNotNull(aggregate.createdAt());
    }

    @Test
    void actualizarCambiaLosDatosYDejaElEventoDeActualizacion() {
        PatientAllergy aggregate = PatientAllergy.register(UUID.randomUUID(), "valor-a", "valor-a", "valor-a", LocalDateTime.now(), UUID.randomUUID());
        aggregate.clearDomainEvents();

        aggregate.update(UUID.randomUUID(), "valor-b", "valor-b", "valor-b", LocalDateTime.now(), UUID.randomUUID());

        assertEquals("valor-b", aggregate.substance());
        assertEquals(1, aggregate.domainEvents().size());
        assertInstanceOf(PatientAllergyUpdatedEvent.class, aggregate.domainEvents().getFirst());
    }

    @Test
    void registrarConUnCampoObligatorioNuloLanzaDomainException() {
        assertThrows(DomainException.class, () -> PatientAllergy.register(null, "valor-a", "valor-a", "valor-a", LocalDateTime.now(), UUID.randomUUID()));
    }
}
