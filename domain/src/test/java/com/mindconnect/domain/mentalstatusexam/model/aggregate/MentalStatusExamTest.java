package com.mindconnect.domain.mentalstatusexam.model.aggregate;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.mentalstatusexam.event.MentalStatusExamRegisteredEvent;
import com.mindconnect.domain.mentalstatusexam.event.MentalStatusExamUpdatedEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MentalStatusExamTest {

    @Test
    void registrarDejaElEventoDeRegistro() {
        MentalStatusExam aggregate = MentalStatusExam.register(UUID.randomUUID(), "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", UUID.randomUUID());

        assertEquals(1, aggregate.domainEvents().size());
        MentalStatusExamRegisteredEvent event = assertInstanceOf(
                MentalStatusExamRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void registrarGeneraElIdYLosValoresPorDefecto() {
        MentalStatusExam aggregate = MentalStatusExam.register(UUID.randomUUID(), "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", UUID.randomUUID());

        assertNotNull(aggregate.id());
        assertNotNull(aggregate.createdAt());
    }

    @Test
    void actualizarCambiaLosDatosYDejaElEventoDeActualizacion() {
        MentalStatusExam aggregate = MentalStatusExam.register(UUID.randomUUID(), "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", UUID.randomUUID());
        aggregate.clearDomainEvents();

        aggregate.update(UUID.randomUUID(), "valor-b", "valor-b", "valor-b", "valor-b", "valor-b", "valor-b", "valor-b", "valor-b", "valor-b", "valor-b", "valor-b", "valor-b", "valor-b", "valor-b", "valor-b", "valor-b", "valor-b");

        assertEquals("valor-b", aggregate.appearance());
        assertEquals(1, aggregate.domainEvents().size());
        assertInstanceOf(MentalStatusExamUpdatedEvent.class, aggregate.domainEvents().getFirst());
    }

    @Test
    void registrarConUnCampoObligatorioNuloLanzaDomainException() {
        assertThrows(DomainException.class, () -> MentalStatusExam.register(null, "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", UUID.randomUUID()));
    }
}
