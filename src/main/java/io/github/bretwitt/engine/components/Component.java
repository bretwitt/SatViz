package io.github.bretwitt.engine.components;

import io.github.bretwitt.SatViz;

public abstract class Component {

    private SatViz satViz;

    public Component(SatViz satViz) {
        this.satViz = satViz;
    }

    public SatViz getSatViz() {
        return satViz;
    }

    public abstract void onInitialize();
    public abstract void onEnable();
    public abstract void update(float tpf);
    public abstract void onDisable();

}
