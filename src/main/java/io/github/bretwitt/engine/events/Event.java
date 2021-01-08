package io.github.bretwitt.engine.events;

public class Event {
    private Object data;

    public Object getData() {
        return data;
    }
    public Event(Object data) {
        this.data = data;
    }
}
