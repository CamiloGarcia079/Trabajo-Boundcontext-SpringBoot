package com.mindconnect.domain.assessmenttype.model.aggregate;


import org.junit.jupiter.api.Test;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.assessmenttype.event.AssessmentTypeRegisteredEvent;
import com.mindconnect.domain.assessmenttype.event.AssessmentTypeUpdatedEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AssessmentTypeTest {

    @Test
    void registrarDejaElEventoDeRegistro() {
        AssessmentType aggregate = AssessmentType.register("valor-a", "valor-a", "valor-a");

        assertEquals(1, aggregate.domainEvents().size());
        AssessmentTypeRegisteredEvent event = assertInstanceOf(
                AssessmentTypeRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void registrarGeneraElIdYLosValoresPorDefecto() {
        AssessmentType aggregate = AssessmentType.register("valor-a", "valor-a", "valor-a");

        assertNotNull(aggregate.id());
        assertTrue(aggregate.active());
        assertNotNull(aggregate.createdAt());
    }

    @Test
    void actualizarCambiaLosDatosYDejaElEventoDeActualizacion() {
        AssessmentType aggregate = AssessmentType.register("valor-a", "valor-a", "valor-a");
        aggregate.clearDomainEvents();

        aggregate.update("valor-b", "valor-b", "valor-b");

        assertEquals("valor-b", aggregate.code());
        assertEquals(1, aggregate.domainEvents().size());
        assertInstanceOf(AssessmentTypeUpdatedEvent.class, aggregate.domainEvents().getFirst());
    }

    @Test
    void registrarConUnCampoObligatorioNuloLanzaDomainException() {
        assertThrows(DomainException.class, () -> AssessmentType.register(null, "valor-a", "valor-a"));
    }
}
