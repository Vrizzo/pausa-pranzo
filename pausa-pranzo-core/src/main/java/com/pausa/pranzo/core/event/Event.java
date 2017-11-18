package com.pausa.pranzo.core.event;

import java.time.LocalDateTime;

public final class Event<T> {

    private final LocalDateTime publishingDate;
    private final EventType type;
    private final T body;

    public Event(LocalDateTime publishingDate, EventType type, T body) {
        this.publishingDate = publishingDate;
        this.type = type;
        this.body = body;
    }

    public LocalDateTime getPublishingDate() {
        return publishingDate;
    }

    public EventType getType() {
        return type;
    }

    public T getBody() {
        return body;
    }
}
