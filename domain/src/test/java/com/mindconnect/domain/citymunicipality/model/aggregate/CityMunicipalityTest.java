package com.mindconnect.domain.citymunicipality.model.aggregate;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.citymunicipality.event.CityMunicipalityRegisteredEvent;
import com.mindconnect.domain.citymunicipality.event.CityMunicipalityUpdatedEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CityMunicipalityTest {

    @Test
    void registrarDejaElEventoDeRegistro() {
        CityMunicipality aggregate = CityMunicipality.register("valor-a", "valor-a", "valor-a", UUID.randomUUID());

        assertEquals(1, aggregate.domainEvents().size());
        CityMunicipalityRegisteredEvent event = assertInstanceOf(
                CityMunicipalityRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void registrarGeneraElIdYLosValoresPorDefecto() {
        CityMunicipality aggregate = CityMunicipality.register("valor-a", "valor-a", "valor-a", UUID.randomUUID());

        assertNotNull(aggregate.id());
        assertTrue(aggregate.isActive());
        assertNotNull(aggregate.createdAt());
    }

    @Test
    void actualizarCambiaLosDatosYDejaElEventoDeActualizacion() {
        CityMunicipality aggregate = CityMunicipality.register("valor-a", "valor-a", "valor-a", UUID.randomUUID());
        aggregate.clearDomainEvents();

        aggregate.update("valor-b", "valor-b", "valor-b", UUID.randomUUID());

        assertEquals("valor-b", aggregate.nameCity());
        assertEquals(1, aggregate.domainEvents().size());
        assertInstanceOf(CityMunicipalityUpdatedEvent.class, aggregate.domainEvents().getFirst());
    }

    @Test
    void registrarConUnCampoObligatorioNuloLanzaDomainException() {
        assertThrows(DomainException.class, () -> CityMunicipality.register(null, "valor-a", "valor-a", UUID.randomUUID()));
    }
}
