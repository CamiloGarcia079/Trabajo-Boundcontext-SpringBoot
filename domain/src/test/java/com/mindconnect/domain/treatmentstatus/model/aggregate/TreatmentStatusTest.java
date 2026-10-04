package com.mindconnect.domain.treatmentstatus.model.aggregate;


import org.junit.jupiter.api.Test;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.treatmentstatus.event.TreatmentStatusRegisteredEvent;
import com.mindconnect.domain.treatmentstatus.event.TreatmentStatusUpdatedEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TreatmentStatusTest {

    @Test
    void registrarDejaElEventoDeRegistro() {
        TreatmentStatus aggregate = TreatmentStatus.register("valor-a", "valor-a");

        assertEquals(1, aggregate.domainEvents().size());
        TreatmentStatusRegisteredEvent event = assertInstanceOf(
                TreatmentStatusRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void registrarGeneraElIdYLosValoresPorDefecto() {
        TreatmentStatus aggregate = TreatmentStatus.register("valor-a", "valor-a");

        assertNotNull(aggregate.id());
        assertTrue(aggregate.active());
        assertNotNull(aggregate.createdAt());
    }

    @Test
    void actualizarCambiaLosDatosYDejaElEventoDeActualizacion() {
        TreatmentStatus aggregate = TreatmentStatus.register("valor-a", "valor-a");
        aggregate.clearDomainEvents();

        aggregate.update("valor-b", "valor-b");

        assertEquals("valor-b", aggregate.code());
        assertEquals(1, aggregate.domainEvents().size());
        assertInstanceOf(TreatmentStatusUpdatedEvent.class, aggregate.domainEvents().getFirst());
    }

    @Test
    void registrarConUnCampoObligatorioNuloLanzaDomainException() {
        assertThrows(DomainException.class, () -> TreatmentStatus.register(null, "valor-a"));
    }
}
