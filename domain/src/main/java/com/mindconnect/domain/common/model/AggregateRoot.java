package com.mindconnect.domain.common.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.mindconnect.domain.common.event.DomainEvent;

/**
 * Clase base de todos los agregados. Guarda los eventos de dominio que van ocurriendo
 * para que la capa de aplicación los pueda leer después.
 */
public abstract class AggregateRoot {

    private final List<DomainEvent> domainEvents = new ArrayList<>();

    protected final void recordEvent(DomainEvent event) {
        domainEvents.add(Objects.requireNonNull(event, "event must not be null"));
    }

    public final List<DomainEvent> domainEvents() {
        return List.copyOf(domainEvents);
    }

    public final void clearDomainEvents() {
        domainEvents.clear();
    }
}
