package io.github.bretwitt.engine.appstates;

import com.google.common.eventbus.EventBus;
import com.jme3.app.Application;
import com.jme3.app.state.BaseAppState;
import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.entities.Entity;

import java.util.ArrayList;
import java.util.List;

public abstract class AppState extends BaseAppState {


    List<Entity> stateEntities = new ArrayList<>();
    SatViz satViz;
    EventBus stateEventBus;

    @Override
    protected void initialize(Application app) {
        satViz = (SatViz) app;
        initializeState();
        stateEntities.forEach(Entity::onStateInitialize);
    }

    protected void addEntity(Entity e) {
        stateEntities.add(e);
    }

    @Override
    protected void cleanup(Application app) {
    }


    @Override
    protected void onEnable() {
        onStateEnable();
        stateEntities.forEach(Entity::onStateEnable);
    }

    @Override
    protected void onDisable() {
        onStateDisable();
        stateEntities.forEach(Entity::onStateDisable);
    }

    @Override
    public void update(float tpf) {
        stateUpdate(tpf);
        stateEntities.forEach(e -> e.onStateUpdate(tpf));
    }

    public EventBus getStateEventBus() {
        if(stateEventBus == null)
            stateEventBus = new EventBus();
        return stateEventBus;
    }

    protected abstract void initializeState();
    protected abstract void cleanupState();
    protected abstract void onStateEnable();
    protected abstract void onStateDisable();
    protected abstract void stateUpdate(float tpf);


}
