package io.github.bretwitt.engine.components;

import com.jme3.scene.Spatial;
import io.github.bretwitt.SatViz;

public class SpatialComponent extends BaseSpatialComponent {

    public SpatialComponent(Spatial spatial, SatViz app) {
        super(app);
        this.spatial = spatial;
    }

    public SpatialComponent(SatViz app) {
        super(app);
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

    }
}
