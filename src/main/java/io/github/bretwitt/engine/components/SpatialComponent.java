package io.github.bretwitt.engine.components;

import com.google.common.eventbus.EventBus;
import com.jme3.scene.Spatial;
import io.github.bretwitt.SatViz;

public class SpatialComponent extends BaseSpatialComponent {

    public SpatialComponent(Spatial spatial, EventBus eventBus, SatViz app) {
        super(eventBus, app);
        this.spatial = spatial;
    }

    public SpatialComponent(EventBus eventBus, SatViz app) {
        super(eventBus, app);
    }

    @Override
    public void onInitialize() {

    }

    public Spatial getSpatial() {
        return spatial;
    }

    @Override
    public void onEnable() {
        updateSpatial(spatial);
    }

    @Override
    public void update(float tpf) {

    }

    @Override
    public void onDisable() {
        getSatViz().getRootNode().detachChild(getSpatial());
    }
}
