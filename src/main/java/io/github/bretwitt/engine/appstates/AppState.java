package io.github.bretwitt.engine.appstates;

import com.jme3.app.Application;
import com.jme3.app.state.BaseAppState;
import io.github.bretwitt.engine.entities.Entity;

import java.util.ArrayList;
import java.util.List;

public abstract class AppState extends BaseAppState {


    List<Entity> stateEntities = new ArrayList<>();

    @Override
    protected void initialize(Application app) {
        initializeState();
    }

    protected void addEntity(Entity e) {
        stateEntities.add(e);
    }

    @Override
    protected void cleanup(Application app) {
    }


    @Override
    protected void onEnable() {
        stateEntities.forEach(Entity::onEnable);
        onStateEnable();
    }

    @Override
    protected void onDisable() {
        stateEntities.forEach(Entity::onDisable);
        onStateDisable();
    }

    @Override
    public void update(float tpf) {
        stateEntities.forEach(e -> e.onUpdate(tpf));
        stateUpdate(tpf);
    }

    protected abstract void initializeState();
    protected abstract void cleanupState();
    protected abstract void onStateEnable();
    protected abstract void onStateDisable();
    protected abstract void stateUpdate(float tpf);


}
