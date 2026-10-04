package com.mindconnect.domain.treatmentgoal.model.aggregate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.treatmentgoal.event.TreatmentGoalRegisteredEvent;
import com.mindconnect.domain.treatmentgoal.event.TreatmentGoalUpdatedEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TreatmentGoalTest {

    @Test
    void registrarDejaElEventoDeRegistro() {
        TreatmentGoal aggregate = TreatmentGoal.register(UUID.randomUUID(), "valor-a", LocalDate.now(), LocalDateTime.now(), "valor-a", UUID.randomUUID());

        assertEquals(1, aggregate.domainEvents().size());
        TreatmentGoalRegisteredEvent event = assertInstanceOf(
                TreatmentGoalRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void registrarGeneraElIdYLosValoresPorDefecto() {
        TreatmentGoal aggregate = TreatmentGoal.register(UUID.randomUUID(), "valor-a", LocalDate.now(), LocalDateTime.now(), "valor-a", UUID.randomUUID());

        assertNotNull(aggregate.id());
        assertNotNull(aggregate.createdAt());
    }

    @Test
    void actualizarCambiaLosDatosYDejaElEventoDeActualizacion() {
        TreatmentGoal aggregate = TreatmentGoal.register(UUID.randomUUID(), "valor-a", LocalDate.now(), LocalDateTime.now(), "valor-a", UUID.randomUUID());
        aggregate.clearDomainEvents();

        aggregate.update(UUID.randomUUID(), "valor-b", LocalDate.now(), LocalDateTime.now(), "valor-b", UUID.randomUUID());

        assertEquals("valor-b", aggregate.description());
        assertEquals(1, aggregate.domainEvents().size());
        assertInstanceOf(TreatmentGoalUpdatedEvent.class, aggregate.domainEvents().getFirst());
    }

    @Test
    void registrarConUnCampoObligatorioNuloLanzaDomainException() {
        assertThrows(DomainException.class, () -> TreatmentGoal.register(null, "valor-a", LocalDate.now(), LocalDateTime.now(), "valor-a", UUID.randomUUID()));
    }
}
