package com.mindconnect.domain.medicationroute.model.aggregate;


import org.junit.jupiter.api.Test;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.medicationroute.event.MedicationRouteRegisteredEvent;
import com.mindconnect.domain.medicationroute.event.MedicationRouteUpdatedEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MedicationRouteTest {

    @Test
    void registrarDejaElEventoDeRegistro() {
        MedicationRoute aggregate = MedicationRoute.register("valor-a", "valor-a");

        assertEquals(1, aggregate.domainEvents().size());
        MedicationRouteRegisteredEvent event = assertInstanceOf(
                MedicationRouteRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void registrarGeneraElIdYLosValoresPorDefecto() {
        MedicationRoute aggregate = MedicationRoute.register("valor-a", "valor-a");

        assertNotNull(aggregate.id());
        assertTrue(aggregate.active());
        assertNotNull(aggregate.createdAt());
    }

    @Test
    void actualizarCambiaLosDatosYDejaElEventoDeActualizacion() {
        MedicationRoute aggregate = MedicationRoute.register("valor-a", "valor-a");
        aggregate.clearDomainEvents();

        aggregate.update("valor-b", "valor-b");

        assertEquals("valor-b", aggregate.code());
        assertEquals(1, aggregate.domainEvents().size());
        assertInstanceOf(MedicationRouteUpdatedEvent.class, aggregate.domainEvents().getFirst());
    }

    @Test
    void registrarConUnCampoObligatorioNuloLanzaDomainException() {
        assertThrows(DomainException.class, () -> MedicationRoute.register(null, "valor-a"));
    }
}
