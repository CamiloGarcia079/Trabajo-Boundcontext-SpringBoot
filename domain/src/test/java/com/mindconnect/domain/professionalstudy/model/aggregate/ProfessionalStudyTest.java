package com.mindconnect.domain.professionalstudy.model.aggregate;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.professionalstudy.event.ProfessionalStudyRegisteredEvent;
import com.mindconnect.domain.professionalstudy.event.ProfessionalStudyUpdatedEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProfessionalStudyTest {

    @Test
    void registrarDejaElEventoDeRegistro() {
        ProfessionalStudy aggregate = ProfessionalStudy.register(UUID.randomUUID(), UUID.randomUUID(), "valor-a", "valor-a", true, "valor-a", UUID.randomUUID());

        assertEquals(1, aggregate.domainEvents().size());
        ProfessionalStudyRegisteredEvent event = assertInstanceOf(
                ProfessionalStudyRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void registrarGeneraElIdYLosValoresPorDefecto() {
        ProfessionalStudy aggregate = ProfessionalStudy.register(UUID.randomUUID(), UUID.randomUUID(), "valor-a", "valor-a", true, "valor-a", UUID.randomUUID());

        assertNotNull(aggregate.id());
        assertNotNull(aggregate.createdAt());
    }

    @Test
    void actualizarCambiaLosDatosYDejaElEventoDeActualizacion() {
        ProfessionalStudy aggregate = ProfessionalStudy.register(UUID.randomUUID(), UUID.randomUUID(), "valor-a", "valor-a", true, "valor-a", UUID.randomUUID());
        aggregate.clearDomainEvents();

        aggregate.update(UUID.randomUUID(), UUID.randomUUID(), "valor-b", "valor-b", false, "valor-b", UUID.randomUUID());

        assertEquals("valor-b", aggregate.title());
        assertEquals(1, aggregate.domainEvents().size());
        assertInstanceOf(ProfessionalStudyUpdatedEvent.class, aggregate.domainEvents().getFirst());
    }

    @Test
    void registrarConUnCampoObligatorioNuloLanzaDomainException() {
        assertThrows(DomainException.class, () -> ProfessionalStudy.register(null, UUID.randomUUID(), "valor-a", "valor-a", true, "valor-a", UUID.randomUUID()));
    }
}
