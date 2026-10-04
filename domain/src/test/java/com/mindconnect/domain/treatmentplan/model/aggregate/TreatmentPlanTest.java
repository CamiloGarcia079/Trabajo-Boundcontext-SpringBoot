package com.mindconnect.domain.treatmentplan.model.aggregate;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.treatmentplan.event.TreatmentPlanRegisteredEvent;
import com.mindconnect.domain.treatmentplan.event.TreatmentPlanUpdatedEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TreatmentPlanTest {

    @Test
    void registrarDejaElEventoDeRegistro() {
        TreatmentPlan aggregate = TreatmentPlan.register(UUID.randomUUID(), UUID.randomUUID(), "valor-a", "valor-a", LocalDate.now(), LocalDate.now(), UUID.randomUUID());

        assertEquals(1, aggregate.domainEvents().size());
        TreatmentPlanRegisteredEvent event = assertInstanceOf(
                TreatmentPlanRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void registrarGeneraElIdYLosValoresPorDefecto() {
        TreatmentPlan aggregate = TreatmentPlan.register(UUID.randomUUID(), UUID.randomUUID(), "valor-a", "valor-a", LocalDate.now(), LocalDate.now(), UUID.randomUUID());

        assertNotNull(aggregate.id());
        assertNotNull(aggregate.createdAt());
    }

    @Test
    void actualizarCambiaLosDatosYDejaElEventoDeActualizacion() {
        TreatmentPlan aggregate = TreatmentPlan.register(UUID.randomUUID(), UUID.randomUUID(), "valor-a", "valor-a", LocalDate.now(), LocalDate.now(), UUID.randomUUID());
        aggregate.clearDomainEvents();

        aggregate.update(UUID.randomUUID(), UUID.randomUUID(), "valor-b", "valor-b", LocalDate.now(), LocalDate.now(), UUID.randomUUID());

        assertEquals("valor-b", aggregate.title());
        assertEquals(1, aggregate.domainEvents().size());
        assertInstanceOf(TreatmentPlanUpdatedEvent.class, aggregate.domainEvents().getFirst());
    }

    @Test
    void registrarConUnCampoObligatorioNuloLanzaDomainException() {
        assertThrows(DomainException.class, () -> TreatmentPlan.register(null, UUID.randomUUID(), "valor-a", "valor-a", LocalDate.now(), LocalDate.now(), UUID.randomUUID()));
    }
}
