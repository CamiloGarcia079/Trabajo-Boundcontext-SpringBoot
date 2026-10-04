package com.mindconnect.domain.treatmentgoalstatus.model.aggregate;


import org.junit.jupiter.api.Test;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.treatmentgoalstatus.event.TreatmentGoalStatusRegisteredEvent;
import com.mindconnect.domain.treatmentgoalstatus.event.TreatmentGoalStatusUpdatedEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TreatmentGoalStatusTest {

    @Test
    void registrarDejaElEventoDeRegistro() {
        TreatmentGoalStatus aggregate = TreatmentGoalStatus.register("valor-a", "valor-a");

        assertEquals(1, aggregate.domainEvents().size());
        TreatmentGoalStatusRegisteredEvent event = assertInstanceOf(
                TreatmentGoalStatusRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void registrarGeneraElIdYLosValoresPorDefecto() {
        TreatmentGoalStatus aggregate = TreatmentGoalStatus.register("valor-a", "valor-a");

        assertNotNull(aggregate.id());
        assertTrue(aggregate.active());
        assertNotNull(aggregate.createdAt());
    }

    @Test
    void actualizarCambiaLosDatosYDejaElEventoDeActualizacion() {
        TreatmentGoalStatus aggregate = TreatmentGoalStatus.register("valor-a", "valor-a");
        aggregate.clearDomainEvents();

        aggregate.update("valor-b", "valor-b");

        assertEquals("valor-b", aggregate.code());
        assertEquals(1, aggregate.domainEvents().size());
        assertInstanceOf(TreatmentGoalStatusUpdatedEvent.class, aggregate.domainEvents().getFirst());
    }

    @Test
    void registrarConUnCampoObligatorioNuloLanzaDomainException() {
        assertThrows(DomainException.class, () -> TreatmentGoalStatus.register(null, "valor-a"));
    }
}
