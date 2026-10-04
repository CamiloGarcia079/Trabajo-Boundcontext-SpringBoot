package com.mindconnect.domain.common.model;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.mindconnect.domain.common.event.DomainEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AggregateRootTest {

    @Test
    void guardaElEventoRegistrado() {
        AgregadoDePrueba aggregate = new AgregadoDePrueba();
        DomainEvent event = new EventoDePrueba(LocalDateTime.now());

        aggregate.registrar(event);

        assertEquals(List.of(event), aggregate.domainEvents());
    }

    @Test
    void laListaDeEventosNoSePuedeModificarDesdeAfuera() {
        AgregadoDePrueba aggregate = new AgregadoDePrueba();
        aggregate.registrar(new EventoDePrueba(LocalDateTime.now()));

        assertThrows(UnsupportedOperationException.class, () -> aggregate.domainEvents().clear());
    }

    @Test
    void rechazaUnEventoNulo() {
        AgregadoDePrueba aggregate = new AgregadoDePrueba();

        assertThrows(NullPointerException.class, () -> aggregate.registrar(null));
    }

    @Test
    void clearDomainEventsLimpiaLaLista() {
        AgregadoDePrueba aggregate = new AgregadoDePrueba();
        aggregate.registrar(new EventoDePrueba(LocalDateTime.now()));

        aggregate.clearDomainEvents();

        assertTrue(aggregate.domainEvents().isEmpty());
    }

    private static final class AgregadoDePrueba extends AggregateRoot {
        private void registrar(DomainEvent event) {
            recordEvent(event);
        }
    }

    private record EventoDePrueba(LocalDateTime occurredOn) implements DomainEvent {
    }
}
