package com.mindconnect.domain.country.model.aggregate;


import org.junit.jupiter.api.Test;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.country.event.CountryRegisteredEvent;
import com.mindconnect.domain.country.event.CountryUpdatedEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CountryTest {

    @Test
    void registrarDejaElEventoDeRegistro() {
        Country aggregate = Country.register("valor-a", "valor-a", "valor-a", "valor-a");

        assertEquals(1, aggregate.domainEvents().size());
        CountryRegisteredEvent event = assertInstanceOf(
                CountryRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void registrarGeneraElIdYLosValoresPorDefecto() {
        Country aggregate = Country.register("valor-a", "valor-a", "valor-a", "valor-a");

        assertNotNull(aggregate.id());
        assertTrue(aggregate.isActive());
        assertNotNull(aggregate.createdAt());
    }

    @Test
    void actualizarCambiaLosDatosYDejaElEventoDeActualizacion() {
        Country aggregate = Country.register("valor-a", "valor-a", "valor-a", "valor-a");
        aggregate.clearDomainEvents();

        aggregate.update("valor-b", "valor-b", "valor-b", "valor-b");

        assertEquals("valor-b", aggregate.nameCountry());
        assertEquals(1, aggregate.domainEvents().size());
        assertInstanceOf(CountryUpdatedEvent.class, aggregate.domainEvents().getFirst());
    }

    @Test
    void registrarConUnCampoObligatorioNuloLanzaDomainException() {
        assertThrows(DomainException.class, () -> Country.register(null, "valor-a", "valor-a", "valor-a"));
    }
}
