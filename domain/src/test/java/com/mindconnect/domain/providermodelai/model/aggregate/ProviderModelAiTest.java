package com.mindconnect.domain.providermodelai.model.aggregate;


import org.junit.jupiter.api.Test;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.providermodelai.event.ProviderModelAiRegisteredEvent;
import com.mindconnect.domain.providermodelai.event.ProviderModelAiUpdatedEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProviderModelAiTest {

    @Test
    void registrarDejaElEventoDeRegistro() {
        ProviderModelAi aggregate = ProviderModelAi.register("valor-a", "valor-a", "valor-a");

        assertEquals(1, aggregate.domainEvents().size());
        ProviderModelAiRegisteredEvent event = assertInstanceOf(
                ProviderModelAiRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void registrarGeneraElIdYLosValoresPorDefecto() {
        ProviderModelAi aggregate = ProviderModelAi.register("valor-a", "valor-a", "valor-a");

        assertNotNull(aggregate.id());
        assertTrue(aggregate.isActive());
        assertNotNull(aggregate.createdAt());
    }

    @Test
    void actualizarCambiaLosDatosYDejaElEventoDeActualizacion() {
        ProviderModelAi aggregate = ProviderModelAi.register("valor-a", "valor-a", "valor-a");
        aggregate.clearDomainEvents();

        aggregate.update("valor-b", "valor-b", "valor-b");

        assertEquals("valor-b", aggregate.nameProviderAi());
        assertEquals(1, aggregate.domainEvents().size());
        assertInstanceOf(ProviderModelAiUpdatedEvent.class, aggregate.domainEvents().getFirst());
    }

    @Test
    void registrarConUnCampoObligatorioNuloLanzaDomainException() {
        assertThrows(DomainException.class, () -> ProviderModelAi.register(null, "valor-a", "valor-a"));
    }
}
