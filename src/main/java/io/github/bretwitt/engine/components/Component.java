package io.github.bretwitt.engine.components;

import com.google.common.eventbus.EventBus;
import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.appstates.AppState;

public abstract class Component {

    private SatViz satViz;
    private EventBus eventBus;

    public Component(EventBus eventBus, SatViz satViz) {
        this.satViz = satViz;
        this.eventBus = eventBus;
        eventBus.register(this);
    }

    public SatViz getSatViz() {
        return satViz;
    }
    public EventBus getEventBus() { return eventBus; }
    public AppState getAppState() { return satViz.getCurrentState(); }

    public void onInitialize() {}
    public void onEnable() {}
    public void update(float tpf) {}
    public void onDisable() {}

}
