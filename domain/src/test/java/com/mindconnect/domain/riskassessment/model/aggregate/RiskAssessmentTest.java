package com.mindconnect.domain.riskassessment.model.aggregate;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.riskassessment.event.RiskAssessmentRegisteredEvent;
import com.mindconnect.domain.riskassessment.event.RiskAssessmentUpdatedEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RiskAssessmentTest {

    @Test
    void registrarDejaElEventoDeRegistro() {
        RiskAssessment aggregate = RiskAssessment.register(UUID.randomUUID(), UUID.randomUUID(), true, true, true, true, true, "valor-a", "valor-a", "valor-a", "valor-a", LocalDateTime.now(), UUID.randomUUID());

        assertEquals(1, aggregate.domainEvents().size());
        RiskAssessmentRegisteredEvent event = assertInstanceOf(
                RiskAssessmentRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void registrarGeneraElIdYLosValoresPorDefecto() {
        RiskAssessment aggregate = RiskAssessment.register(UUID.randomUUID(), UUID.randomUUID(), true, true, true, true, true, "valor-a", "valor-a", "valor-a", "valor-a", LocalDateTime.now(), UUID.randomUUID());

        assertNotNull(aggregate.id());
    }

    @Test
    void actualizarCambiaLosDatosYDejaElEventoDeActualizacion() {
        RiskAssessment aggregate = RiskAssessment.register(UUID.randomUUID(), UUID.randomUUID(), true, true, true, true, true, "valor-a", "valor-a", "valor-a", "valor-a", LocalDateTime.now(), UUID.randomUUID());
        aggregate.clearDomainEvents();

        aggregate.update(UUID.randomUUID(), UUID.randomUUID(), false, false, false, false, false, "valor-b", "valor-b", "valor-b", "valor-b", LocalDateTime.now(), UUID.randomUUID());

        assertEquals(false, aggregate.suicidalIdeation());
        assertEquals(1, aggregate.domainEvents().size());
        assertInstanceOf(RiskAssessmentUpdatedEvent.class, aggregate.domainEvents().getFirst());
    }

    @Test
    void registrarConUnCampoObligatorioNuloLanzaDomainException() {
        assertThrows(DomainException.class, () -> RiskAssessment.register(null, UUID.randomUUID(), true, true, true, true, true, "valor-a", "valor-a", "valor-a", "valor-a", LocalDateTime.now(), UUID.randomUUID()));
    }
}
