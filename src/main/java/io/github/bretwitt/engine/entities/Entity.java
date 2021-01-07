package io.github.bretwitt.engine.entities;

import com.jme3.app.Application;
import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.components.Component;

import java.util.ArrayList;
import java.util.List;

public abstract class Entity {

    private List<Component> componentList;
    private SatViz satViz;

    public Entity(List<Component> components, SatViz satViz) {
        componentList = components;
        this.satViz = satViz;
    }

    public Entity(SatViz satViz) {
        this.satViz = satViz;
        componentList = new ArrayList<>();
    }

    public void addComponent(Component c){
        componentList.add(c);
    }

    public SatViz getSatViz() {
        return satViz;
    }

    public void onStateInitialize() {
        onEntityInitialize();
        componentList.forEach(Component::onInitialize);
    }
    public void onStateEnable() {
        onEntityEnable();
        componentList.forEach(Component::onEnable);
    }
    public void onStateDisable() {
        onEntityDisable();
        componentList.forEach(Component::onDisable);
    }
    public void onStateUpdate(float tpf) {
        onEntityUpdate();
        componentList.forEach(c -> c.update(tpf));
    }
    public void onEntityInitialize() { }
    public void onEntityEnable() { }
    public void onEntityDisable() { }
    public void onEntityUpdate() { }
}
