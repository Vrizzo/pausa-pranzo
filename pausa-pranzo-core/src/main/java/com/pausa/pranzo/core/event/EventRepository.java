package com.pausa.pranzo.core.event;

public interface EventRepository {

    void publish(Event event);
}
