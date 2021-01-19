package io.github.bretwitt.engine.appstates;

import com.google.common.eventbus.EventBus;
import com.jme3.app.Application;
import com.jme3.app.state.BaseAppState;
import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.appstates.stateeventbus.StateEventBus;
import io.github.bretwitt.engine.entities.Entity;

import java.util.ArrayList;
import java.util.List;

public abstract class AppState extends BaseAppState {


    List<Entity> stateEntities = new ArrayList<>();
    SatViz satViz;
    StateEventBus stateEventBus;

    @Override
    protected void initialize(Application app) {
        satViz = (SatViz) app;
        initializeState();
        stateEntities.forEach(Entity::onInitialize);
    }

    protected void addEntity(Entity e) {
        stateEntities.add(e);
        e.onEntityInitialize();
        e.onEntityEnable();
    }

    @Override
    protected void cleanup(Application app) {
        stateEntities.forEach(Entity::onEntityEnable);
        stateEventBus.unregister(this);
    }


    @Override
    protected void onEnable() {
        onStateEnable();
        stateEntities.forEach(Entity::onEnable);
    }

    @Override
    protected void onDisable() {
        onStateDisable();
        stateEntities.forEach(Entity::onDisable);
    }

    @Override
    public void update(float tpf) {
        stateUpdate(tpf);
        stateEntities.forEach(e -> e.onStateUpdate(tpf));
    }

    public StateEventBus getStateEventBus() {
        if(stateEventBus == null) {
            stateEventBus = new StateEventBus();
            stateEventBus.register(this);
        }
        return stateEventBus;
    }

    protected void initializeState() {}
    protected void cleanupState() {}
    protected void onStateEnable() {}
    protected void onStateDisable() {}
    protected void stateUpdate(float tpf) {}


}
