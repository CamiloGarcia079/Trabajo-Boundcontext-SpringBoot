package com.mindconnect.domain.clinicalnote.model.aggregate;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.clinicalnote.event.ClinicalNoteRegisteredEvent;
import com.mindconnect.domain.clinicalnote.event.ClinicalNoteUpdatedEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ClinicalNoteTest {

    @Test
    void registrarDejaElEventoDeRegistro() {
        ClinicalNote aggregate = ClinicalNote.register(UUID.randomUUID(), UUID.randomUUID(), "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", LocalDateTime.now());

        assertEquals(1, aggregate.domainEvents().size());
        ClinicalNoteRegisteredEvent event = assertInstanceOf(
                ClinicalNoteRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void registrarGeneraElIdYLosValoresPorDefecto() {
        ClinicalNote aggregate = ClinicalNote.register(UUID.randomUUID(), UUID.randomUUID(), "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", LocalDateTime.now());

        assertNotNull(aggregate.id());
        assertNotNull(aggregate.createdAt());
    }

    @Test
    void actualizarCambiaLosDatosYDejaElEventoDeActualizacion() {
        ClinicalNote aggregate = ClinicalNote.register(UUID.randomUUID(), UUID.randomUUID(), "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", LocalDateTime.now());
        aggregate.clearDomainEvents();

        aggregate.update(UUID.randomUUID(), UUID.randomUUID(), "valor-b", "valor-b", "valor-b", "valor-b", "valor-b", LocalDateTime.now());

        assertEquals("valor-b", aggregate.subjective());
        assertEquals(1, aggregate.domainEvents().size());
        assertInstanceOf(ClinicalNoteUpdatedEvent.class, aggregate.domainEvents().getFirst());
    }

    @Test
    void registrarConUnCampoObligatorioNuloLanzaDomainException() {
        assertThrows(DomainException.class, () -> ClinicalNote.register(null, UUID.randomUUID(), "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", LocalDateTime.now()));
    }
}
