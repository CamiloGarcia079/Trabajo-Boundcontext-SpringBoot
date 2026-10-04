package com.mindconnect.domain.patientcontact.model.aggregate;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.patientcontact.event.PatientContactRegisteredEvent;
import com.mindconnect.domain.patientcontact.event.PatientContactUpdatedEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PatientContactTest {

    @Test
    void registrarDejaElEventoDeRegistro() {
        PatientContact aggregate = PatientContact.register(UUID.randomUUID(), UUID.randomUUID(), true, true, UUID.randomUUID());

        assertEquals(1, aggregate.domainEvents().size());
        PatientContactRegisteredEvent event = assertInstanceOf(
                PatientContactRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void registrarGeneraElIdYLosValoresPorDefecto() {
        PatientContact aggregate = PatientContact.register(UUID.randomUUID(), UUID.randomUUID(), true, true, UUID.randomUUID());

        assertNotNull(aggregate.id());
    }

    @Test
    void actualizarCambiaLosDatosYDejaElEventoDeActualizacion() {
        PatientContact aggregate = PatientContact.register(UUID.randomUUID(), UUID.randomUUID(), true, true, UUID.randomUUID());
        aggregate.clearDomainEvents();

        aggregate.update(UUID.randomUUID(), UUID.randomUUID(), false, false, UUID.randomUUID());

        assertEquals(false, aggregate.isPrimaryContact());
        assertEquals(1, aggregate.domainEvents().size());
        assertInstanceOf(PatientContactUpdatedEvent.class, aggregate.domainEvents().getFirst());
    }

    @Test
    void registrarConUnCampoObligatorioNuloLanzaDomainException() {
        assertThrows(DomainException.class, () -> PatientContact.register(null, UUID.randomUUID(), true, true, UUID.randomUUID()));
    }
}
