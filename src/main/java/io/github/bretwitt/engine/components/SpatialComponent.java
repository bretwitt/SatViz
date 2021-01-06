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

    public Spatial getSpatial() {
        return spatial;
    }

    @Override
    public void initialize() {

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
