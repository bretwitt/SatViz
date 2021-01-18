package io.github.bretwitt.engine.entities;

import com.google.common.eventbus.EventBus;
import com.jme3.app.Application;
import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.components.Component;

import java.util.ArrayList;
import java.util.List;

public abstract class Entity {

    private List<Component> componentList;
    private SatViz satViz;
    private EventBus eventBus;

    public Entity(List<Component> components, SatViz satViz) {
        componentList = components;
        this.satViz = satViz;
        eventBus = new EventBus();
    }

    public Entity(SatViz satViz) {
        this.satViz = satViz;
        componentList = new ArrayList<>();
        eventBus = new EventBus();
    }

    public void addComponent(Component c){
        componentList.add(c);
        c.onInitialize();
        c.onEnable();
    }

    public SatViz getSatViz() {
        return satViz;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public void onInitialize() {
        onEntityInitialize();
        componentList.forEach(Component::onInitialize);
    }
    public void onEnable() {
        onEntityEnable();
        componentList.forEach(Component::onEnable);
    }
    public void onDisable() {
        onEntityDisable();
        componentList.forEach(Component::onDisable);
    }
    public void onStateUpdate(float tpf) {
        onEntityUpdate(tpf);
        componentList.forEach(c -> c.update(tpf));
    }
    public void onEntityInitialize() { }
    public void onEntityEnable() { }
    public void onEntityDisable() { }
    public void onEntityUpdate(float tpf) { }
}
