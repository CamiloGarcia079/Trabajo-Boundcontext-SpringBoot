package com.mindconnect.domain.study.model.aggregate;


import org.junit.jupiter.api.Test;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.study.event.StudyRegisteredEvent;
import com.mindconnect.domain.study.event.StudyUpdatedEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StudyTest {

    @Test
    void registrarDejaElEventoDeRegistro() {
        Study aggregate = Study.register("valor-a");

        assertEquals(1, aggregate.domainEvents().size());
        StudyRegisteredEvent event = assertInstanceOf(
                StudyRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void registrarGeneraElIdYLosValoresPorDefecto() {
        Study aggregate = Study.register("valor-a");

        assertNotNull(aggregate.id());
        assertNotNull(aggregate.createdAt());
    }

    @Test
    void actualizarCambiaLosDatosYDejaElEventoDeActualizacion() {
        Study aggregate = Study.register("valor-a");
        aggregate.clearDomainEvents();

        aggregate.update("valor-b");

        assertEquals("valor-b", aggregate.name());
        assertEquals(1, aggregate.domainEvents().size());
        assertInstanceOf(StudyUpdatedEvent.class, aggregate.domainEvents().getFirst());
    }

    @Test
    void registrarConUnCampoObligatorioNuloLanzaDomainException() {
        assertThrows(DomainException.class, () -> Study.register(null));
    }
}
