package io.github.bretwitt.engine.components;

import com.google.common.eventbus.EventBus;
import io.github.bretwitt.SatViz;

public abstract class Component {

    private SatViz satViz;
    private EventBus eventBus;
    private EventBus stateEventBus;

    public Component(EventBus eventBus, SatViz satViz) {
        this.satViz = satViz;
        this.eventBus = eventBus;
        eventBus.register(this);
    }

    public SatViz getSatViz() {
        return satViz;
    }
    public EventBus getEventBus() { return eventBus; }

    public abstract void onInitialize();
    public abstract void onEnable();
    public abstract void update(float tpf);
    public abstract void onDisable();

}
