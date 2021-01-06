package io.github.bretwitt.engine.entities;

import com.jme3.app.Application;
import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.components.Component;

import java.util.ArrayList;
import java.util.List;

public abstract class Entity {

    List<Component> componentList;
    SatViz satViz;

    public Entity(List<Component> components, SatViz satViz) {
        componentList = components;
        this.satViz = satViz;
    }

    public Entity(SatViz satViz) {
        this.satViz = satViz;
        componentList = new ArrayList<>();
    }

    public void setComponentList(List<Component> c) {
        componentList = c;
    }

    public void addComponent(Component c){
        componentList.add(c);
    }

    public SatViz getSatViz() {
        return satViz;
    }

    public void onInitialize() {
        componentList.forEach(Component::initialize);
    }

    public void onEnable() {
        componentList.forEach(Component::onEnable);
    }
    public void onDisable() {
        componentList.forEach(Component::onDisable);
    }
    public void onUpdate(float tpf) { componentList.forEach(c -> c.update(tpf)); }
}
