package com.mindconnect.domain.professionaltype.model.aggregate;


import org.junit.jupiter.api.Test;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.professionaltype.event.ProfessionalTypeRegisteredEvent;
import com.mindconnect.domain.professionaltype.event.ProfessionalTypeUpdatedEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProfessionalTypeTest {

    @Test
    void registrarDejaElEventoDeRegistro() {
        ProfessionalType aggregate = ProfessionalType.register("valor-a");

        assertEquals(1, aggregate.domainEvents().size());
        ProfessionalTypeRegisteredEvent event = assertInstanceOf(
                ProfessionalTypeRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void registrarGeneraElIdYLosValoresPorDefecto() {
        ProfessionalType aggregate = ProfessionalType.register("valor-a");

        assertNotNull(aggregate.id());
        assertNotNull(aggregate.createdAt());
    }

    @Test
    void actualizarCambiaLosDatosYDejaElEventoDeActualizacion() {
        ProfessionalType aggregate = ProfessionalType.register("valor-a");
        aggregate.clearDomainEvents();

        aggregate.update("valor-b");

        assertEquals("valor-b", aggregate.name());
        assertEquals(1, aggregate.domainEvents().size());
        assertInstanceOf(ProfessionalTypeUpdatedEvent.class, aggregate.domainEvents().getFirst());
    }

    @Test
    void registrarConUnCampoObligatorioNuloLanzaDomainException() {
        assertThrows(DomainException.class, () -> ProfessionalType.register(null));
    }
}
